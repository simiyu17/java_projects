package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.student.KafkaMessage;
import com.student.KafkaSender;
import com.student.TopicType;
import com.student.dao.student.StudentDaoImpl;
import com.student.model.FeeStatus;
import com.student.model.Response;
import com.student.model.Student;

@RestController
@RequestMapping("/students")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentDaoImpl studentdao;

    @Autowired
    KafkaSender producer;

    // -------------------Retrieve All
    // Students---------------------------------------------
    @GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<Student> listAllStudents() {
        List<Student> students = studentdao.getStudents();
        return students;
    }

    // -------------------Retrieve Single
    // Student------------------------------------------
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getStudent(@PathVariable("id") Long id) {
        Student student = studentdao.findById(id);
        if (student == null) {
            return new ResponseEntity<Response>(new Response(false, "Student with id " + id + " not found!!"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    // -------------------Create a
    // Student-------------------------------------------
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createStudent(@RequestBody Student student, UriComponentsBuilder ucBuilder)
            throws Exception {
        try {
            student.setFeeStatus(FeeStatus.HAVE_ARREARS);
            studentdao.save(student);
            return new ResponseEntity<Response>(new Response(true, "Successfully Created Student !!"),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(false, "An Error Occured " + e.getMessage()),
                    HttpStatus.NOT_ACCEPTABLE);
        }

    }

    // ------------------- Update a Student -------------------------------
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") Long id, @RequestBody Student student) throws Exception {
        try {
            Student currentStudent = studentdao.findById(id);
            student.setId(id);
            if (currentStudent == null) {
                return new ResponseEntity<Response>(new Response(false, "Student with id " + id + " not found !!"),
                        HttpStatus.NOT_FOUND);
            }
            studentdao.save(student);
            return new ResponseEntity<Response>(new Response(true, "Successfully Updated Student."), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(false, "An Error Occured " + e.getMessage()),
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }

    // ------------------- Delete a Student-----------------------------------------
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id) throws Exception {

        try {
            Student student = studentdao.findById(id);
            if (student == null) {
                return new ResponseEntity<Response>(new Response(false, "Student with id " + id + " not found !!"),
                        HttpStatus.NOT_FOUND);
            }
            studentdao.delete(student);

            // DELETE STUDENT RECORDS IN OTHER SERVICES
            // create `ObjectMapper` instance
            ObjectMapper mapper = new ObjectMapper();
            // create a JSON object
            ObjectNode std = mapper.createObjectNode();
            std.put("id", student.getId());
            std.put("deletestudentrecords", true);
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(std);
            producer.send(new KafkaMessage(TopicType.DELETE_STUDENT_DETAILS, json));

            return new ResponseEntity<Response>(new Response(true, "Successfully Removed Student."), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(false, "An Error Occured " + e.getMessage()),
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
