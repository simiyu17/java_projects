package com.openapi.service;

import java.util.List;

import com.openapi.entity.Book;
import com.openapi.exception.BookNotFoundException;


public interface BookServiceI {

	Book createBook(Book book) throws Exception;
	
	Book findBookById(Long id) throws BookNotFoundException;
	
	List<Book> getAvailableBooks();
	
	void deleteBook(Long id)throws BookNotFoundException;
	
	
}
