package com.mapstruct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mapstruct.dto.StudentDto;
import com.mapstruct.exception.StudentNotFoundException;
import com.mapstruct.service.StudentServiceI;

@RestController
public class StudentController {

	@Autowired
	private StudentServiceI studentService;
	
	@PostMapping("/")
    public StudentDto createStudent(@RequestBody StudentDto studentDto) {
       return studentService.createStudent(studentDto);
    }

    @GetMapping("/") 
    public ResponseEntity<List<StudentDto>> getAllStudents(){
       return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public StudentDto findStudentById(@PathVariable Long id) throws StudentNotFoundException {
       return studentService.findStudentById(id);
    }

    @PutMapping("/{id}")
    public StudentDto updateStudent(@RequestBody StudentDto studentDto, @PathVariable Long id) throws StudentNotFoundException {
       return studentService.updateStudent(studentDto, id);
    }

    @DeleteMapping("/{id}")
    public String deleteStudentById(@PathVariable Long id) throws StudentNotFoundException {
    	studentService.deleteStudentById(id);
       return String.format("Employee with id: %d Deleted !", id);
    }
}
