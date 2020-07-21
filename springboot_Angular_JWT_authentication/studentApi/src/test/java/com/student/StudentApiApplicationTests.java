package com.student;

import java.net.URI;
import java.net.URISyntaxException;

import com.student.model.Gender;
import com.student.model.Response;
import com.student.model.Student;
import com.student.model.UserInfo;
import org.json.JSONArray;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@TestMethodOrder(OrderAnnotation.class)
class StudentApiApplicationTests extends AbstractTest {

    //CREATE USER
    @Order(1)
    @Test
    public void test1() {
        UserInfo user = new UserInfo("TESTUSERNAME", "TESTPASSWORD123", "TEST FULLNAME");
        URI targetUrl = UriComponentsBuilder.fromUriString("/users/").queryParam("user", user).build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<UserInfo> request = new HttpEntity<>(user, headers);

        ResponseEntity<Response> responseEntity = restTemplate.postForEntity(targetUrl, request, Response.class);

        // Verify request succeed
        Assertions.assertNotNull(responseEntity.getBody().getSuccess());
        int statusCode = responseEntity.getStatusCodeValue();
        Assertions.assertTrue((statusCode == HttpStatus.CREATED.value() || statusCode == HttpStatus.CONFLICT.value()));
    }

    //LOGIN
    @Order(2)
    @Test
    public void test2() {
        UserInfo user = new UserInfo("TESTUSERNAME", "TESTPASSWORD123", "TEST FULLNAME");

        //LOGIN
        URI loginUrl = UriComponentsBuilder.fromUriString("/users/authenticate").queryParam("user", user).build().encode().toUri();

        HttpHeaders headers2 = new HttpHeaders();
        headers2.set("X-COM-PERSIST", "true");
        HttpEntity<UserInfo> request2 = new HttpEntity<>(user, headers2);

        ResponseEntity<Response> loginResponse = restTemplate.postForEntity(loginUrl, request2, Response.class);

        // Verify request succeed
        Assertions.assertNotNull(loginResponse.getBody().getSuccess());
        Assertions.assertNotNull(loginResponse.getBody().getToken());
        Assertions.assertEquals(HttpStatus.OK.value(), loginResponse.getStatusCodeValue());

    }

    //ADD STUDENT WITHOUT JWT TOKEN
    @Order(3)
    @Test
    public void test3() throws URISyntaxException {
        Student student = new Student("TESTNAME", Gender.FEMALE, "12/12/1999", "TESTCITY", "TESTCOUNTRY");
        URI targetUrl = UriComponentsBuilder.fromUriString("/students/").queryParam("student", student).build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<Student> request = new HttpEntity<>(student, headers);

        ResponseEntity<Response> responseEntity = restTemplate.postForEntity(targetUrl, request, Response.class);

        // Verify request succeed
        Assertions.assertNotNull(responseEntity.getBody().getSuccess());
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), responseEntity.getStatusCodeValue());
    }

    //ADD STUDENT WITH JWT TOKEN
    @Order(4)
    @Test
    public void test4() throws URISyntaxException {
        UserInfo user = new UserInfo("TESTUSERNAME", "TESTPASSWORD123", "TEST FULLNAME");

        //LOGIN
        URI loginUrl = UriComponentsBuilder.fromUriString("/users/authenticate").queryParam("user", user).build().encode().toUri();

        HttpHeaders headers2 = new HttpHeaders();
        headers2.set("X-COM-PERSIST", "true");
        HttpEntity<UserInfo> request2 = new HttpEntity<>(user, headers2);

        ResponseEntity<Response> loginResponse = restTemplate.postForEntity(loginUrl, request2, Response.class);
        String jwtToken = loginResponse.getBody().getToken();

        //ADD STUDENT WITH JWT
        Student student = new Student("TESTNAME", Gender.FEMALE, "12/12/1999", "TESTCITY", "TESTCOUNTRY");
        URI addStudentUrl = UriComponentsBuilder.fromUriString("/students/").queryParam("student", student).build().encode().toUri();

        HttpHeaders headers3 = new HttpHeaders();
        headers3.set("X-COM-PERSIST", "true");
        headers3.set("Authorization", "Bearer " + jwtToken);
        HttpEntity<Student> request3 = new HttpEntity<>(student, headers3);

        ResponseEntity<Response> responseEntity = restTemplate.postForEntity(addStudentUrl, request3, Response.class);

        // Verify request succeed
        Assertions.assertNotNull(responseEntity.getBody().getSuccess());
        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
    }

    //REMOVE STUDENT TEST DATA
    @Order(5)
    @Test
    public void test5() throws Exception {
        UserInfo user = new UserInfo("TESTUSERNAME", "TESTPASSWORD123", "TEST FULLNAME");

        //LOGIN
        URI loginUrl = UriComponentsBuilder.fromUriString("/users/authenticate").queryParam("user", user).build().encode().toUri();

        HttpHeaders headers2 = new HttpHeaders();
        headers2.set("X-COM-PERSIST", "true");
        HttpEntity<UserInfo> request2 = new HttpEntity<>(user, headers2);

        ResponseEntity<Response> loginResponse = restTemplate.postForEntity(loginUrl, request2, Response.class);
        String jwtToken = loginResponse.getBody().getToken();

        //DELETE STUDENTS
        URI targetUrl = UriComponentsBuilder.fromUriString("/students/").build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        headers.set("Authorization", "Bearer " + jwtToken);
        HttpEntity<UserInfo> request = new HttpEntity<>(null, headers);

        //JSONArray responseBodyjson = new JSONArray(restTemplate.exchange(targetUrl, HttpMethod.GET, request,  String.class));
        ResponseEntity<Student[]> responseEntity = restTemplate.exchange(targetUrl, HttpMethod.GET, request, Student[].class);
        Student[] studentssarray = responseEntity.getBody();

        //Student[] studentssarray = super.mapFromJson(responseBodyjson.toString(), Student[].class);
        for (Student u : studentssarray) {
            if (u.getName().equals("TESTNAME")) {
                URI deleteUrl = UriComponentsBuilder.fromUriString("/students/" + u.getId()).build().encode().toUri();
                HttpHeaders headers3 = new HttpHeaders();
                headers3.set("X-COM-PERSIST", "true");
                headers3.set("Authorization", "Bearer " + jwtToken);
                HttpEntity<UserInfo> request3 = new HttpEntity<>(null, headers3);

                ResponseEntity<Response> deleteResponseEntity = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, request3, Response.class);
                Assertions.assertNotNull(deleteResponseEntity.getBody().getSuccess());
                Assertions.assertEquals(HttpStatus.OK.value(), deleteResponseEntity.getStatusCodeValue());

            }
        }
        Assertions.assertNotNull(responseEntity);
    }

    //REMOVE USER TEST DATA
    @Order(6)
    @Test
    public void test6() throws Exception {
        URI targetUrl = UriComponentsBuilder.fromUriString("/users/").build().encode().toUri();
        JSONArray responseBodyjson = new JSONArray(restTemplate.getForObject(targetUrl, String.class));

        UserInfo[] usersarray = super.mapFromJson(responseBodyjson.toString(), UserInfo[].class);
        for (UserInfo u : usersarray) {
            if (u.getUsername().equals("TESTUSERNAME")) {
                URI deleteUrl = UriComponentsBuilder.fromUriString("/users/" + u.getId()).build().encode().toUri();
                HttpHeaders headers = new HttpHeaders();
                headers.set("X-COM-PERSIST", "true");
                HttpEntity<UserInfo> request = new HttpEntity<>(null, headers);

                ResponseEntity<Response> responseEntity = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, request, Response.class);
                Assertions.assertNotNull(responseEntity.getBody().getSuccess());
                Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());

            }
        }
        Assertions.assertNotNull(responseBodyjson);
    }

}
