package com.openapi.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.openapi.entity.Book;
import com.openapi.exception.BookNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "book", description = "This is book API")
@RequestMapping("/api/v1/books")
public interface BookControllerI {

	@Operation(summary = "Create book", description = "This can only be done to create a book.", tags = { "book" })
	@ApiResponses(value = { @ApiResponse(description = "Done successfully", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)),
			@Content(mediaType = "application/xml", schema = @Schema(implementation = Book.class)) }) })
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> postBook(
			@NotNull @Parameter(description = "Created book object", required = true) @Valid @RequestBody Book body)
			throws Exception;

	@Operation(summary = "Find book by ID", description = "Returns a book", tags = { "book" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful Found", content = @Content(schema = @Schema(implementation = Book.class))),
			@ApiResponse(responseCode = "400", description = "Invalid ID provided", content = @Content),
			@ApiResponse(responseCode = "404", description = "Book not found", content = @Content) })
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> findBookById(
			@Parameter(description = "ID of the book", required = true) @PathVariable long id) throws Exception;

	@Operation(summary = "Get all books", description = "Returns all available books", tags = { "book" })
	@GetMapping("/")
	public ResponseEntity<List<Book>> findAllBooks();

	@Operation(summary = "Find book by ID", description = "Returns Successs", tags = { "book" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful Found and Deleted", content = @Content(schema = @Schema(implementation = Book.class))),
			@ApiResponse(responseCode = "400", description = "Invalid ID provided", content = @Content),
			@ApiResponse(responseCode = "404", description = "Book not found", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable final long id)throws BookNotFoundException;
}
