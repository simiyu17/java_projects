package com.redis.controller;

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

import com.redis.entiy.Student;
import com.redis.exception.StudentNotFoundException;
import com.redis.service.StudentServiceI;

@RestController
public class StudentController {

	@Autowired
	private StudentServiceI studentService;
	
	@PostMapping("/")
    public Student createStudent(@RequestBody Student student) {
       return studentService.createStudent(student);
    }

    @GetMapping("/") 
    public ResponseEntity<List<Student>> getAllStudents(){
       return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable Long id) throws StudentNotFoundException {
       return studentService.findStudentById(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable Long id) throws StudentNotFoundException {
       return studentService.updateStudent(student, id);
    }

    @DeleteMapping("/{id}")
    public String deleteStudentById(@PathVariable Long id) throws StudentNotFoundException {
    	studentService.deleteStudentById(id);
       return String.format("Employee with id: %d Deleted !", id);
    }
}
