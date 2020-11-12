package com.revature.exception;

public class UserDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = -3204311254412719229L;

	public UserDoesNotExistException(String message) {
		super(message);
	}
}
