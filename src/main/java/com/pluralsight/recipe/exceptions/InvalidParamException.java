package com.pluralsight.recipe.exceptions;

public class InvalidParamException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String exceptionMessage;

	public InvalidParamException(String message) {
		super(message);
		this.exceptionMessage = message;
	}

	public InvalidParamException() {
		super();
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

}
