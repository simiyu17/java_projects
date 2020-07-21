package com.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.dao.bookcrud.BookDaoImpl;
import com.book.model.Book;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookDaoImpl bookdao;

    // -------------------Retrieve All Books---------------------------------------------
    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Book> listAllBooks() {
        List<Book> books = bookdao.getBooks();
        return books;
    }

    // -------------------Retrieve Single Book------------------------------------------
    @CrossOrigin
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getBook(@PathVariable("id") Long id) {
        Book book = bookdao.findById(id);
        if (book == null) {
            return new ResponseEntity<Response>(new Response(false, "Book with id " + id + " not found.", null, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    // -------------------Create a Book-------------------------------------------
    @CrossOrigin
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        try {
            bookdao.save(book);
            return new ResponseEntity<Response>(new Response(true, "Successfully Created Book.", null, null), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<Response>(new Response(false, "An Error Occured " + ex.getMessage(), null, null), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    // ------------------- Update a Book ------------------------------------------------
    @CrossOrigin
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        try {
            if (bookdao.findById(id) == null) {
                return new ResponseEntity<Response>(new Response(false, "Book with id " + id + " not found.", null, null), HttpStatus.NOT_FOUND);
            }

            book.setId(id);
            bookdao.save(book);
            return new ResponseEntity<Response>(new Response(true, "Successfully Updated Book.", null, null), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Response>(new Response(false, "An Error Occured " + ex.getMessage(), null, null), HttpStatus.OK);
        }
    }

    // ------------------- Delete a Book-----------------------------------------
    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        try {
            if (bookdao.findById(id) == null) {
                return new ResponseEntity<Response>(new Response(false, "Book with id " + id + " not found.", null, null), HttpStatus.NOT_FOUND);
            }
            bookdao.delete(bookdao.findById(id));
            return new ResponseEntity<Response>(new Response(true, "Successfully Updated Book.", null, null), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Response>(new Response(false, "An Error Occured " + ex.getMessage(), null, null), HttpStatus.OK);
        }
    }

}
