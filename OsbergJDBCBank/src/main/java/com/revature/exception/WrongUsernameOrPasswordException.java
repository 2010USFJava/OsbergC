package com.revature.exception;

public class WrongUsernameOrPasswordException extends RuntimeException {

	private static final long serialVersionUID = 1816434969370885050L;

	public WrongUsernameOrPasswordException(String message) {
		super(message);
	}

}
