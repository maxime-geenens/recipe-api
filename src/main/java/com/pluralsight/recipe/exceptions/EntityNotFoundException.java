package com.pluralsight.recipe.exceptions;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String exceptionMessage;

	public EntityNotFoundException(String message) {
		super(message);
		this.exceptionMessage = message;
	}

	public EntityNotFoundException() {
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

}
