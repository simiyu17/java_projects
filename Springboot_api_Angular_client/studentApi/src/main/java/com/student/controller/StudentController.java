package com.student.controller;

import com.student.dao.student.StudentDaoImpl;
import com.student.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/students")
@CrossOrigin
public class StudentController {
    
    @Autowired
    private StudentDaoImpl studentdao;

    // -------------------Retrieve All Students---------------------------------------------

    @GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Student> listAllStudents() {
          List<Student> students = studentdao.getStudents();
        return students;
    }

    // -------------------Retrieve Single Student------------------------------------------
    @SuppressWarnings("unchecked")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getStudent(@PathVariable("id") Long id) {
        Student student = studentdao.findById(id);
        if (student == null) {
            return new ResponseEntity(new CustomErrorType("Student with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    // -------------------Create a Student-------------------------------------------
   
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createStudent(@RequestBody Student student, UriComponentsBuilder ucBuilder) throws Exception {

        studentdao.save(student);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/").buildAndExpand(student.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Student ------------------------------------------------
   
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") Long id, @RequestBody Student student) throws Exception {

        Student currentStudent = studentdao.findById(id);

        student.setId(id);

        if (currentStudent == null) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }

        studentdao.save(student);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    // ------------------- Delete a Student-----------------------------------------
    @SuppressWarnings("unchecked")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id) throws Exception {

        Student student = studentdao.findById(id);
        if (student == null) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. Student with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        studentdao.delete(student);
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }
}