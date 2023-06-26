package com.pluralsight.recipe.exceptions;

public class EntityWasNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String exceptionMessage;

	public EntityWasNotFoundException(String message) {
		super(message);
		this.exceptionMessage = message;
	}

	public EntityWasNotFoundException() {
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

}
