package com.student;

import com.student.model.Gender;
import com.student.model.Response;
import com.student.model.Student;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

class StudentApiApplicationTests extends AbstractTest {

    @Autowired
    private TestRestTemplate restTemplate;
    
    
    
    @Test
    public void testAddStudent() throws Exception {

        Student student = new Student("TESTNAME", Gender.FEMALE, "TESTCITY", "TESTCOUNTRY");
        URI targetUrl = UriComponentsBuilder.fromUriString("/v1/students/").queryParam("student", student).build().encode().toUri();
        
         HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<Student> request = new HttpEntity<>(student, headers);
        
        ResponseEntity<Response> responseEntity = restTemplate.postForEntity(targetUrl, request, Response.class);

        // Verify request succeed
        Assertions.assertNotNull(responseEntity.getBody().getSuccess());
        Assertions.assertEquals(201, responseEntity.getStatusCodeValue(), "Error");
    }
    
      @Test
    public void testAddStudentWithNullValues_Should_Return_406() throws Exception {

        Student student = new Student(null, null, "TESTCITY", "TESTCOUNTRY");
        URI targetUrl = UriComponentsBuilder.fromUriString("/v1/students/").queryParam("student", student).build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<Student> request = new HttpEntity<>(student, headers);

        ResponseEntity<Response> responseEntity = restTemplate.postForEntity(targetUrl, request, Response.class);

        // Verify request succeed
        Assertions.assertNotNull(responseEntity.getBody().getSuccess());
        Assertions.assertEquals(responseEntity.getBody().getSuccess(), false);
        Assertions.assertEquals(406, responseEntity.getStatusCodeValue(), "Not Accepted");
    }
    
    
    @Test
    public void testGetAllStudents() throws Exception {
        URI targetUrl = UriComponentsBuilder.fromUriString("/v1/students/").build().encode().toUri();

        JSONArray responseBodyjson = new JSONArray(restTemplate.getForObject(targetUrl, String.class));

        List<Student> studentlist = Arrays.asList(super.mapFromJson(responseBodyjson.toString(), Student[].class));
        
        System.out.println("STUDENTS JSONArray*************************************************"+responseBodyjson.toString());
        
        // Verify request succeed
        Assertions.assertNotNull(responseBodyjson);
       // Assertions.assertTrue(responseBodyjson.toString().contains("title"));
    }

    @Test
    public void testRemoveTestData() throws Exception {
        URI targetUrl = UriComponentsBuilder.fromUriString("/v1/students/").build().encode().toUri();
        JSONArray responseBodyjson = new JSONArray(restTemplate.getForObject(targetUrl, String.class));

        Student[] studentarray = super.mapFromJson(responseBodyjson.toString(), Student[].class);
        for (Student b : studentarray) {
            if (b.getName().equals("TESTNAME")) {
                URI deleteUrl = UriComponentsBuilder.fromUriString("/v1/students/" + b.getId()).build().encode().toUri();
                HttpHeaders headers = new HttpHeaders();
                headers.set("X-COM-PERSIST", "true");
                HttpEntity<Student> request = new HttpEntity<>(null, headers);

                ResponseEntity<Response> responseEntity = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, request, Response.class);
                Assertions.assertNotNull(responseEntity.getBody().getSuccess());
                Assertions.assertEquals(200, responseEntity.getStatusCodeValue());

            }
        }
        Assertions.assertNotNull(responseBodyjson);
        //Assertions.assertTrue(responseBodyjson.toString().contains("title"));
    }



}
