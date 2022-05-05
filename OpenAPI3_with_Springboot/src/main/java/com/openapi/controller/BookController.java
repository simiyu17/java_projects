package com.openapi.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.openapi.entity.Book;
import com.openapi.exception.BookNotFoundException;
import com.openapi.service.BookServiceI;

@RestController
public class BookController implements BookControllerI{
	
	@Autowired
	private BookServiceI bookService;

	@Override
	public ResponseEntity<Book> postBook(@NotNull @Valid Book body) throws Exception {
		return ResponseEntity.ok(bookService.createBook(body));
	}

	@Override
	public ResponseEntity<Book> findBookById(long id) throws Exception {
		return ResponseEntity.ok(bookService.findBookById(id));
	}

	@Override
	public ResponseEntity<List<Book>> findAllBooks() {
		return ResponseEntity.ok(bookService.getAvailableBooks());
	}

	@Override
	public ResponseEntity<String> deleteBook(long id) throws BookNotFoundException {
		bookService.deleteBook(id);
		return ResponseEntity.ok("Book Deleted Successfully");
	}

}
