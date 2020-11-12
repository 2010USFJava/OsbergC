package com.revature.exception;

public class NonZeroBalanceException extends RuntimeException {

	private static final long serialVersionUID = -7619285018493006734L;

	public NonZeroBalanceException(String message) {
		super(message);
	}

}
