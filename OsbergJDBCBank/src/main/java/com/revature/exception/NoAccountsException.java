package com.revature.exception;

public class NoAccountsException extends RuntimeException {

	private static final long serialVersionUID = -401844024755607712L;
	
	public NoAccountsException(String message) {
		super(message);
	}
}
