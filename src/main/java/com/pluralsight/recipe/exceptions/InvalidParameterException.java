package com.pluralsight.recipe.exceptions;

public class InvalidParameterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String exceptionMessage;

	public InvalidParameterException(String message) {
		super(message);
		this.exceptionMessage = message;
	}

	public InvalidParameterException() {
		super();
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

}
