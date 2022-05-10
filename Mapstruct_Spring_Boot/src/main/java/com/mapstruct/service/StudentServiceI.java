package com.mapstruct.service;

import java.util.List;

import com.mapstruct.dto.StudentDto;
import com.mapstruct.exception.StudentNotFoundException;

public interface StudentServiceI {

	StudentDto createStudent(StudentDto studentDto);
	
	StudentDto updateStudent(StudentDto studentDto, Long id) throws StudentNotFoundException;
	
	StudentDto findStudentById(Long id) throws StudentNotFoundException;
	
	List<StudentDto> getAllStudents();
	
	void deleteStudentById(Long id) throws StudentNotFoundException;
}
