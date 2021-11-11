package com.iiht.training.elibrary.exception;

public class BookNotFoundException extends RuntimeException{

	public BookNotFoundException(String message) {
		super(message);
	}
}
