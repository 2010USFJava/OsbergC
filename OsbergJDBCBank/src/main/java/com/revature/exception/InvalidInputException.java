package com.revature.exception;

public class InvalidInputException extends RuntimeException {

	private static final long serialVersionUID = -4170041778415346801L;

	public InvalidInputException(String message) {
		super(message);
	}

}
