package com.mapstruct.dto;

import java.time.LocalDate;

public class StudentDto {

	private Long studentId;
	
	private String studentfirstName;
	
	private String studentLastName;
	
	private LocalDate dob;

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStudentfirstName() {
		return studentfirstName;
	}

	public void setStudentfirstName(String studentfirstName) {
		this.studentfirstName = studentfirstName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	
	
}
