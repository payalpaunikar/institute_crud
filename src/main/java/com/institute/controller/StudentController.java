package com.institute.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.institute.datamodel.Student;
import com.institute.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	 private StudentService studentService;
	 
	 @Autowired
	 public StudentController(StudentService studentService) {
		 this.studentService = studentService;
	 }
	 
	 @PostMapping("/")
	 public Student createStudent(@RequestBody Student student) {
		 return studentService.addStudent(student);
	 }
	 
	 @GetMapping("/all")
	 public List<Student> getAllStudents(){
		 return studentService.getAllStudents();
	 }
	 
	 
	 @GetMapping("/{id}")
	 public Student getStudentById(@PathVariable Long id) {
		 return studentService.getStudentById(id);
	 }
	 
	 @PutMapping("/{id}")
	 public Student updateStudent(@PathVariable Long id,@RequestBody Student student) {
		 return studentService.updateStudent(id, student);
	 }
	 
	 
	 @DeleteMapping("/{id}")
	 public String deleteStudent(@PathVariable Long id) {
		 return studentService.deleteStudent(id);
	 }

}
