package com.redis.service;

import java.util.List;

import com.redis.entiy.Student;
import com.redis.exception.StudentNotFoundException;

public interface StudentServiceI {

	Student createStudent(Student student);
	
	Student updateStudent(Student student, Long id) throws StudentNotFoundException;
	
	Student findStudentById(Long id) throws StudentNotFoundException;
	
	List<Student> getAllStudents();
	
	void deleteStudentById(Long id) throws StudentNotFoundException;
}
