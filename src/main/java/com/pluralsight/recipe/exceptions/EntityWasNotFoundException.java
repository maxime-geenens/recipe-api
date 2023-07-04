package com.pluralsight.recipe.exceptions;

public class EntityWasNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String exceptionMessage;

	public EntityWasNotFoundException(String message) {
		super(message);
		this.exceptionMessage = message;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

}
