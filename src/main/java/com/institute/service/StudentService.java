package com.institute.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.institute.datamodel.Student;

@Service
public class StudentService {

	    private final String FILE_PATH = "student.json";
	    private final ObjectMapper objectMapper = new ObjectMapper();
	    
	    
	    private List<Student> readFromFile(){
	    	try {
	    		
	    		File file = new File(FILE_PATH);
	    		if (!file.exists()) {
					return new ArrayList<>();
				}
	    		return objectMapper.readValue(file, new TypeReference<List<Student>>() {});
	    		
	    	}
	    	catch(IOException ex) {
	    		ex.printStackTrace();
	    		return new ArrayList<>();
	    	}
	    }
	    
	    private void writeToFile(List<Student> students) {
	    	try {
	    		objectMapper.writeValue(new File(FILE_PATH), students);
	    	}
	    	catch (IOException ex) {
				ex.printStackTrace();
			}
	    }
	    
	    public Student addStudent(Student student) {
	    	List<Student> students = readFromFile();
	    	long newId = students.isEmpty()?1:students.get(students.size()-1).getId()+1;
	    	student.setId(newId);
	    	students.add(student);
	    	writeToFile(students);
	    	return student;
	    }
	    
	    public List<Student> getAllStudents() {
	        return readFromFile();
	    }
	    
	    public Student getStudentById(Long id) {
	        return readFromFile().stream()
	                .filter(student -> student.getId().equals(id))
	                .findFirst()
	                .orElse(null);
	    }
	    
	    public Student updateStudent(Long id, Student updatedStudent) {
	        List<Student> students = readFromFile();
	        Optional<Student> optionalStudent = students.stream().filter(s -> s.getId().equals(id)).findFirst();
	        if (optionalStudent.isPresent()) {
	            Student existingStudent = optionalStudent.get();
	            existingStudent.setFirstName(updatedStudent.getFirstName());
	            existingStudent.setLastName(updatedStudent.getLastName());
	            existingStudent.setAge(updatedStudent.getAge());
	            existingStudent.setAddress(updatedStudent.getAddress());
	            existingStudent.setClassName(updatedStudent.getClassName());
	            existingStudent.setClassDivision(updatedStudent.getClassDivision());
	            existingStudent.setMobileNo(updatedStudent.getMobileNo());
	            writeToFile(students);
	            return existingStudent;
	        }
	        return null;
	    }
	    
	    public String deleteStudent(Long id) {
	        List<Student> students = readFromFile();
	        boolean removed = students.removeIf(student -> student.getId().equals(id));
	        if (removed) {
	            writeToFile(students);
	            return "Student with ID " + id + " deleted successfully!";
	        }
	        return "Student not found!";
	    }
}
