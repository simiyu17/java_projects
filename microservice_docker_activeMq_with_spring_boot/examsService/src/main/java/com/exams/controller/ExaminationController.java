package com.exams.controller;

import java.net.URI;
import java.util.List;

import com.exams.dao.exam.ExaminationDaoImpl;
import com.exams.model.Examination;
import com.exams.model.FeeStatus;
import com.exams.model.Response;
import com.exams.model.Student;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/scores")
@CrossOrigin
public class ExaminationController {

    @Autowired
    private ExaminationDaoImpl studentdao;

    @Autowired
    DiscoveryClient discoveryClient;

    // -------------------Retrieve All Examinations Scores ---------------------------------------------

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<Examination> listAllExaminations(@RequestParam(name = "studentId", required = true) Long studentId) {
        List<Examination> students = studentdao.getExaminations(studentId);
        return students;
    }


    // -------------------Create a Examination-------------------------------------------

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createExamination(@RequestBody Examination exam) throws Exception {

        try {
            List<ServiceInstance> list = discoveryClient.getInstances("student-service");
            ServiceInstance studentServiceInstances = list.get(0);
            URI studentURI = studentServiceInstances.getUri();
            RestTemplate restTemplate = new RestTemplate();

            Student std = restTemplate.getForObject(studentURI + "/students/{studentId}", Student.class, exam.getStudentId());

            if (std == null) {
                return new ResponseEntity<Response>(new Response(false, "No student with id:"+exam.getStudentId()+" Found"), HttpStatus.NOT_FOUND);
            }
            if (std.getFeeStatus() != FeeStatus.FULLY_PAID) {
                return new ResponseEntity<Response>(new Response(false, "Student HAS NOT HAVE FULLY PAID fees (1000 units) as required"), HttpStatus.NOT_ACCEPTABLE);
            }
            
            studentdao.save(exam);
            return new ResponseEntity<Response>(new Response(true, "Successfully Created Examination Record !!"), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Response>(new Response(false, "An Error Occured " + e.getMessage()),  HttpStatus.NOT_ACCEPTABLE);
        }

    }

    // ------------------- Delete a Examination-----------------------------------------
   
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteExamination(@PathVariable("id") Long id) throws Exception {

        try {
            Examination student = studentdao.findById(id);
            if (student == null) {
                return new ResponseEntity<Response>(new Response(false, "Examination with id " + id + " not found !!"),  HttpStatus.NOT_FOUND);
            }
            studentdao.delete(student);
            return new ResponseEntity<Response>(new Response(true, "Successfully Removed Examination."), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(false, "An Error Occured " + e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }
}