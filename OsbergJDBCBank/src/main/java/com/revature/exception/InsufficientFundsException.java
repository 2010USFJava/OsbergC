package com.revature.exception;

public class InsufficientFundsException extends RuntimeException {

	private static final long serialVersionUID = -6987466399051184211L;

	public InsufficientFundsException(String message) {
		super(message);
	}

}
