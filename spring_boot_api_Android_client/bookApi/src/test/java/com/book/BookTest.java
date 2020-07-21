/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book;

import com.book.controller.Response;
import com.book.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author simiyu
 */
@TestMethodOrder(OrderAnnotation.class)
public class BookTest extends AbstractTest {

    @Autowired
    private TestRestTemplate restTemplate;

  

    @Test
    public void testAddBook() throws Exception {

        Book book = new Book("TESTTITLE", "TESTAUTHOR");
        URI targetUrl = UriComponentsBuilder.fromUriString("/books/").queryParam("book", book).build().encode().toUri();
        
         HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<Book> request = new HttpEntity<>(book, headers);
        
        ResponseEntity<Response> responseEntity = restTemplate.postForEntity(targetUrl, request, Response.class);

        // Verify request succeed
        Assertions.assertNotNull(responseEntity.getBody().getSuccess());
        Assertions.assertEquals(201, responseEntity.getStatusCodeValue(), "Error");
    }
    
      @Test
    public void testAddBookWithNullValues_Should_Return_406() throws Exception {

        Book book = new Book(null, null);
        URI targetUrl = UriComponentsBuilder.fromUriString("/books/").queryParam("book", book).build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<Book> request = new HttpEntity<>(book, headers);

        ResponseEntity<Response> responseEntity = restTemplate.postForEntity(targetUrl, request, Response.class);

        // Verify request succeed
        Assertions.assertNotNull(responseEntity.getBody().getSuccess());
        Assertions.assertEquals(responseEntity.getBody().getSuccess(), false);
        Assertions.assertEquals(406, responseEntity.getStatusCodeValue(), "Not Accepted");
    }
    
    
    @Test
    public void testGetAllBooks() throws Exception {
        URI targetUrl = UriComponentsBuilder.fromUriString("/books/").build().encode().toUri();

        JSONArray responseBodyjson = new JSONArray(restTemplate.getForObject(targetUrl, String.class));

        List<Book> booklist = Arrays.asList(super.mapFromJson(responseBodyjson.toString(), Book[].class));
        // Verify request succeed
        Assertions.assertNotNull(responseBodyjson);
       // Assertions.assertTrue(responseBodyjson.toString().contains("title"));
    }

    @Test
    public void testRemoveTestData() throws Exception {
        URI targetUrl = UriComponentsBuilder.fromUriString("/books/").build().encode().toUri();
        JSONArray responseBodyjson = new JSONArray(restTemplate.getForObject(targetUrl, String.class));

        Book[] bookarray = super.mapFromJson(responseBodyjson.toString(), Book[].class);
        for (Book b : bookarray) {
            if (b.getTitle().equals("TESTTITLE")) {
                URI deleteUrl = UriComponentsBuilder.fromUriString("/books/" + b.getId()).build().encode().toUri();
                HttpHeaders headers = new HttpHeaders();
                headers.set("X-COM-PERSIST", "true");
                HttpEntity<Book> request = new HttpEntity<>(null, headers);

                ResponseEntity<Response> responseEntity = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, request, Response.class);
                Assertions.assertNotNull(responseEntity.getBody().getSuccess());
                Assertions.assertEquals(200, responseEntity.getStatusCodeValue());

            }
        }
        //List<Book> booklist = Arrays.asList(bookarray);

        // Verify request succeed
        Assertions.assertNotNull(responseBodyjson);
        //Assertions.assertTrue(responseBodyjson.toString().contains("title"));
    }

}
