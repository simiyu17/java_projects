package com.openapi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Book object")
@Entity
@Table(name = "book")
public class Book implements Serializable{

	  private static final long serialVersionUID = -6410140310819497757L;

	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Schema(description = "Unique identifier of the Book Record.", example = "10001", required = true)
	  private long id;
	  
	  @Schema(description = "Book title.", example = "Java Good Practices", required = true)
	  @NotBlank
	  @Size(min = 0, max = 20)
	  @Column(name = "title", nullable = false)
	  private String title;

	  @Schema(description = "Name of the book author.", example = "Daniel Simiyu", required = true)
	  @NotBlank
	  @Size(min = 0, max = 30)
	  @Column(name = "author", nullable = false)
	  private String author; 
	  
	  public  Book() {}
	  
	  public long getId() {
	    return id;
	  }
	  public void setId(long id) {
	    this.id = id;
	  }
	  
	  public String getTitle() {
	    return title;
	  }
	  public void setTitle(String title) {
	    this.title = title;
	  }
	  
	  public String getAuthor() {
	    return author;
	  }
	  public void setAuthor(String author) {
	    this.author = author;
	  }
}
