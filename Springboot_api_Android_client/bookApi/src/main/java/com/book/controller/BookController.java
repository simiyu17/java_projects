package com.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.book.dao.bookcrud.BookDaoImpl;
import com.book.model.Book;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/api")
public class BookController {


    @Autowired
    private BookDaoImpl bookdao;

    // -------------------Retrieve All Books---------------------------------------------

    @GetMapping(value = "/books", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Book> listAllBooks() {
          List<Book> books = bookdao.getBooks();
        return books;
    }

    // -------------------Retrieve Single Book------------------------------------------
    @SuppressWarnings("unchecked")
    @CrossOrigin
    @GetMapping(value = "/books/{id}")
    public ResponseEntity<?> getBook(@PathVariable("id") Long id) {
        Book book = bookdao.findById(id);
        if (book == null) {
            return new ResponseEntity(new CustomErrorType("Book with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    // -------------------Create a Book-------------------------------------------
    @CrossOrigin
    @PostMapping(value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createBook(@RequestBody Book book, UriComponentsBuilder ucBuilder) throws Exception {

        bookdao.save(book);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/books/{id}").buildAndExpand(book.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Book ------------------------------------------------
    @CrossOrigin
    @PutMapping(value = "/books/{id}")
    public ResponseEntity<?> updateBook(@PathVariable("id") Long id, @RequestBody Book book) throws Exception {

        Book currentBook = bookdao.findById(id);

        if (currentBook == null) {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }

        currentBook.setTitle(book.getTitle());
        currentBook.setAuthor(book.getAuthor());

        bookdao.save(currentBook);
        return new ResponseEntity<Book>(currentBook, HttpStatus.OK);
    }

    // ------------------- Delete a Book-----------------------------------------
    @SuppressWarnings("unchecked")
    @CrossOrigin
    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) throws Exception {

        Book book = bookdao.findById(id);
        if (book == null) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. Book with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        bookdao.delete(book);
        return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }

}
