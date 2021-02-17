package com.fees.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fees.KafkaMessage;
import com.fees.KafkaSender;
import com.fees.TopicType;
import com.fees.dao.fees.FeesDaoImpl;
import com.fees.model.Fees;
import com.fees.model.Response;
import com.fees.model.Student;

@RestController
@RequestMapping("/fees")
@CrossOrigin
public class FeesController {

    @Autowired
    private FeesDaoImpl feesdao;

    @Inject
    private RestTemplate restTemplate;

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    KafkaSender producer;

    // -------------------Retrieve All
    // Feess---------------------------------------------
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<Fees> listAllStudentFees(@RequestParam(name = "studentId", required = true) Long studentId) {
        return feesdao.getStudentFees(studentId);
    }


    // -------------------Create a Fees-------------------------------------------
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createFees(@RequestBody Fees fee)
            throws Exception {
        try {
            List<ServiceInstance> list = discoveryClient.getInstances("student-service");
            ServiceInstance studentServiceInstances = list.get(0);
            URI studentURI = studentServiceInstances.getUri();

            Student std = restTemplate.getForObject(studentURI + "/students/{studentId}", Student.class, fee.getStudentId());

            if (std == null) {
                return new ResponseEntity<Response>(new Response(false, "No student with id:"+fee.getStudentId()+" Found"), HttpStatus.NOT_FOUND);
            }

            BigDecimal totpaid = BigDecimal.ZERO;
            for (Fees txn : feesdao.getStudentFees(std.getId())) {
                totpaid = totpaid.add(txn.getAmount());
            }
            totpaid = totpaid.add(fee.getAmount());
            // Full fees is 1000
            if (totpaid.compareTo(new BigDecimal(999)) > 0) {

                //UPDATE STUDENT FEES TO FULLY PAID
                // create `ObjectMapper` instance
                ObjectMapper mapper = new ObjectMapper();
                // create a JSON object
                ObjectNode student = mapper.createObjectNode();
                student.put("id", std.getId());
                student.put("fullypaidfee", true);
                String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
                producer.send(new KafkaMessage(TopicType.FEES_PAID, json));
            }
            feesdao.save(fee);
            return new ResponseEntity<Response>(new Response(true, "Successfully Updated Fees."), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(false, "An Error Occured " + e.getMessage()),
                    HttpStatus.NOT_ACCEPTABLE);
        }

    }


    // ------------------- Delete a Fees-----------------------------------------
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteFees(@PathVariable("id") Long id) throws Exception {

        try {
            Fees fees = feesdao.findById(id);
            if (fees == null) {
                return new ResponseEntity<Response>(new Response(false, "Fees with id " + id + " not found !!"),
                        HttpStatus.NOT_FOUND);
            }
            feesdao.delete(fees);
            return new ResponseEntity<Response>(new Response(true, "Successfully Removed Fees."), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(false, "An Error Occured " + e.getMessage()),
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
