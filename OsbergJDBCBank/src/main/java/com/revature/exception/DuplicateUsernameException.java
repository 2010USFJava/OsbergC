package com.revature.exception;

public class DuplicateUsernameException extends RuntimeException {

	private static final long serialVersionUID = -2286511577703916792L;

	public DuplicateUsernameException(String message) {
		super(message);
	}

}
