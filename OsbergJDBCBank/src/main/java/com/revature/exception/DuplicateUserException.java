package com.revature.exception;

public class DuplicateUserException extends RuntimeException {

	private static final long serialVersionUID = -6862751016467452546L;

	public DuplicateUserException(String message) {
		super(message);
	}

}
