package com.luciano.bowlinggame.exception;

public class FileParsingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileParsingException() {
		super();
	}

	public FileParsingException(String message) {
		super(message);
	}

	public FileParsingException(String message, Throwable cause) {
		super(message, cause);
	}

}
