package com.book.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Basic(optional = false)
	    private Long id;
	

	@Column(name = "name")
	    private String title;
	 
	 @Column(name = "author")
	    private String author;
	 
	 public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
