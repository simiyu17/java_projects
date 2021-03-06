package com.student.controller;

import com.student.config.CommonUtil;
import com.student.dao.student.StudentDaoImpl;
import com.student.model.Response;
import com.student.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/students")
@CrossOrigin
public class StudentController extends CommonUtil{
    
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
   
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getStudent(@PathVariable("id") Long id) {
        Student student = studentdao.findById(id);
        if (student == null) {
            return new ResponseEntity<Response>(new Response(false, "Student with id "+id+" not found!!", null, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    // -------------------Create a Student-------------------------------------------
   
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        try {
            studentdao.save(student);
        return new ResponseEntity<Response>(new Response(true, "Successfully Created Student !!", null, null), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(false, "An Error Occured "+e.getMessage(), null, null), HttpStatus.NOT_ACCEPTABLE);
        }

    }

    // ------------------- Update a Student ------------------------------------------------
   
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        try {
            Student currentStudent = studentdao.findById(id);
        student.setId(id);
        if (currentStudent == null) {
            return new ResponseEntity<Response>(new Response(false, "Student with id "+id+" not found !!", null, null), HttpStatus.NOT_FOUND);
        }
        studentdao.save(student);
        return new ResponseEntity<Response>(new Response(true, "Successfully Updated Student.", null, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(false, "An Error Occured "+e.getMessage(), null, null), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    // ------------------- Delete a Student-----------------------------------------
   
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id) {
        try {
            Student student = studentdao.findById(id);
        if (student == null) {
            return new ResponseEntity<Response>(new Response(false, "Student with id "+id+" not found !!", null, null), HttpStatus.NOT_FOUND);
        }
        studentdao.delete(student);
        return new ResponseEntity<Response>(new Response(true, "Successfully Removed Student.", null, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(false, "An Error Occured "+e.getMessage(), null, null), HttpStatus.NOT_ACCEPTABLE);
        }
    }
}