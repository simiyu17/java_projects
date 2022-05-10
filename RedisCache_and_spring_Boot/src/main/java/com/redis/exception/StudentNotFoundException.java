package com.redis.exception;

public class StudentNotFoundException extends Exception{

	private static final long serialVersionUID = 1188783675247499548L;
	
	public StudentNotFoundException(String msg) {
		super(msg);
	}

}
