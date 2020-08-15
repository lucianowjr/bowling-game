package com.luciano.bowlinggame.exception;

public class InvalidGameException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidGameException() {
		super();
	}

	public InvalidGameException(String message) {
		super(message);
	}

	public InvalidGameException(String message, Throwable cause) {
		super(message, cause);
	}

}
