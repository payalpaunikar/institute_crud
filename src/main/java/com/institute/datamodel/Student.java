package com.institute.datamodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {
	     private Long id;
	     private String firstName;
	     private String lastName;
	     private int age;
	     private int className;
	     private char classDivision;
	     private String address;
	     private Long mobileNo;
}
