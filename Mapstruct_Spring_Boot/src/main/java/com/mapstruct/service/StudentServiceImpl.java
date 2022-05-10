package com.mapstruct.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapstruct.dto.StudentDto;
import com.mapstruct.entiy.Student;
import com.mapstruct.exception.StudentNotFoundException;
import com.mapstruct.mappers.StudentMapper;
import com.mapstruct.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentServiceI {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	StudentMapper studentMapper;

	@Override
	public StudentDto createStudent(StudentDto studentDto) {
		return studentMapper.studentToStudentDto(studentRepository.save(studentMapper.studentDtoToStudent(studentDto)));
	}

	@Override
	public StudentDto updateStudent(StudentDto studentDto, Long stId) throws StudentNotFoundException {
		Student foundStudent = studentRepository.findById(stId)
				.orElseThrow(() -> new StudentNotFoundException(String.format("No Student found with Id %d", stId)));
		foundStudent.setBirthDate(studentDto.getDob());
		foundStudent.setFirstName(studentDto.getStudentfirstName());
		foundStudent.setLastName(studentDto.getStudentLastName());
		return studentMapper.studentToStudentDto(studentRepository.save(foundStudent));	
	}

	@Override
	public StudentDto findStudentById(Long stId) throws StudentNotFoundException {
		return studentMapper.studentToStudentDto(studentRepository.findById(stId)
				.orElseThrow(() -> new StudentNotFoundException(String.format("No Student found with Id %d", stId))));
	}

	@Override
	public List<StudentDto> getAllStudents() {
		return studentMapper.modelsToDtos(studentRepository.findAll());
	}

	@Override
	public void deleteStudentById(Long stId) throws StudentNotFoundException {
		Student foundStudent = studentRepository.findById(stId)
				.orElseThrow(() -> new StudentNotFoundException(String.format("No Student found with Id %d", stId)));
		studentRepository.delete(foundStudent);
	}
}
