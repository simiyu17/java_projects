package com.openapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.openapi.entity.Book;
import com.openapi.exception.BookNotFoundException;
import com.openapi.repo.BookRepository;

@Service
public class BookServiceImpl implements BookServiceI {

	private BookRepository bookRepository;

	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public Book createBook(Book book) throws Exception {
		return bookRepository.save(book);
	}

	@Override
	public Book findBookById(Long id) throws BookNotFoundException {
		Optional<Book> bookOptional = bookRepository.findById(id);
		if (!bookOptional.isPresent()) {
			throw new BookNotFoundException(String.format("No Book Found with Id %d", id));
		}
		return bookOptional.get();
	}

	@Override
	public List<Book> getAvailableBooks() {
		return bookRepository.findAll();
	}

	@Override
	public void deleteBook(Long id) throws BookNotFoundException {
		Book book = this.findBookById(id);
		bookRepository.deleteById(book.getId());

	}

}
