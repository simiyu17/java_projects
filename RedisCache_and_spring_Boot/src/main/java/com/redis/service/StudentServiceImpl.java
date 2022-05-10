package com.redis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.redis.entiy.Student;
import com.redis.exception.StudentNotFoundException;
import com.redis.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentServiceI {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	@CachePut(value="Student", key="#stId")
	public Student updateStudent(Student student, Long stId) throws StudentNotFoundException {
		Student foundStudent = studentRepository.findById(stId)
				.orElseThrow(() -> new StudentNotFoundException(String.format("No Student found with Id %d", stId)));
		foundStudent.setBirthDate(student.getBirthDate());
		foundStudent.setFirstName(student.getFirstName());
		foundStudent.setLastName(student.getLastName());
		return studentRepository.save(foundStudent);	
	}

	@Override
	@Cacheable(value="Student", key="#stId")
	public Student findStudentById(Long stId) throws StudentNotFoundException {
		return studentRepository.findById(stId)
				.orElseThrow(() -> new StudentNotFoundException(String.format("No Student found with Id %d", stId)));
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	@CacheEvict(value="Student", key="#stId")
	public void deleteStudentById(Long stId) throws StudentNotFoundException {
		Student foundStudent = studentRepository.findById(stId)
				.orElseThrow(() -> new StudentNotFoundException(String.format("No Student found with Id %d", stId)));
		studentRepository.delete(foundStudent);
	}
}
