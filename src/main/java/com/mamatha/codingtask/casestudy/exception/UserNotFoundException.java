package com.mamatha.codingtask.casestudy.exception;

public class UserNotFoundException extends Exception {
	private String message;

	public UserNotFoundException(String message) {
		super();
		this.message = message;
	}

}
