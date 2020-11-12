package com.revature.exception;

public class PasswordVerificationException extends RuntimeException {

	private static final long serialVersionUID = 4959647065404897963L;

	public PasswordVerificationException(String message) {
		super(message);
	}

}
