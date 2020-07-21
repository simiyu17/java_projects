package com.crud;

import com.crud.controller.Response;
import com.crud.model.Student;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import org.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class IntegrationTestingApplicationTests extends AbstractTest {

    HttpHeaders headers = new HttpHeaders();

    @Order(2)
    @Test
    public void testGetAllStudents() {
        HttpEntity<Student> request = new HttpEntity<Student>(null, headers);
        ResponseEntity<Student[]> responseEntity = restTemplate.exchange(urlWithPort("/api/students/"), HttpMethod.GET, request, Student[].class);
        Student[] studentssarray = responseEntity.getBody();
        Assertions.assertTrue(studentssarray.length > 0);
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }

    @Order(1)
    @Test
    public void testCreateStudentSuccessfully() throws URISyntaxException {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, 06, 23);
        Student student = new Student("Student One", "TEST TOWN", cal.getTime());
        HttpEntity<Student> request = new HttpEntity<Student>(student, headers);
        URI targetUrl = UriComponentsBuilder.fromUriString("/students/").queryParam("student", student).build().encode().toUri();

        ResponseEntity<Response> responseEntity = restTemplate.postForEntity(targetUrl, request, Response.class);

        // Verify request succeed
        Assertions.assertTrue(responseEntity.getBody().getSuccess());
        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
    }
    
    @Order(4)
    @Test
    public void testRetrieveStudentThatDoesNotExist_Should_Get_404() throws Exception {
        HttpEntity<Student> request = new HttpEntity<Student>(null, headers);
        ResponseEntity<Response> responseEntity = restTemplate.exchange(urlWithPort("/api/students/30000000"), HttpMethod.GET, request, Response.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getStatusCodeValue());

    }
    
    @Order(3)
    @Test
    public void testRetrieveStudentThatExist_Should_Get_200() throws Exception {
        HttpEntity<Student> request = new HttpEntity<Student>(null, headers);
        
        URI targetUrl = UriComponentsBuilder.fromUriString("/students/").build().encode().toUri();
        JSONArray responseBodyjson = new JSONArray(restTemplate.getForObject(targetUrl, String.class));

        Student[] usersarray = super.mapFromJson(responseBodyjson.toString(), Student[].class);
        Student std = usersarray[0];
        
        ResponseEntity<Student> responseEntity = restTemplate.exchange(urlWithPort("/api/students/"+std.getId()), HttpMethod.GET, request, Student.class);
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        Student expected = responseEntity.getBody();
        Assertions.assertEquals(std.getFullName(), expected.getFullName());

    }

    @Order(5)
    @Test
    public void testDeleteStudents() throws Exception {
        URI targetUrl = UriComponentsBuilder.fromUriString("/students/").build().encode().toUri();
        JSONArray responseBodyjson = new JSONArray(restTemplate.getForObject(targetUrl, String.class));

        Student[] usersarray = super.mapFromJson(responseBodyjson.toString(), Student[].class);
        for (Student s : usersarray) {
            if (s.getTown().equals("TEST TOWN")) {
                URI deleteUrl = UriComponentsBuilder.fromUriString("/students/" + s.getId()).build().encode().toUri();
                HttpEntity<Student> request = new HttpEntity<>(s, headers);

                ResponseEntity<Response> responseEntity = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, request, Response.class);
                Assertions.assertNotNull(responseEntity.getBody().getSuccess());
                Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());

            }
        }
        Assertions.assertNotNull(responseBodyjson);
    }

    private String urlWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
