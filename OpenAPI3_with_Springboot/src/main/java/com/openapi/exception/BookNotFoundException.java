package com.openapi.exception;

public class BookNotFoundException extends Exception{

	private static final long serialVersionUID = -2680778408294454138L;

	public BookNotFoundException(String msg) {
		super(msg);
	}
}
