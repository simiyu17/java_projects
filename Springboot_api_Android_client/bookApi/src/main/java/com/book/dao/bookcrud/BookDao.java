package com.book.dao.bookcrud;

import java.util.List;

import com.book.model.Book;

public interface BookDao {
        
        Book save(Book book) throws Exception;
	
	Book findById(Long id);
	
	List<Book> getBooks();
	
	void delete(Book book) throws Exception;

}
