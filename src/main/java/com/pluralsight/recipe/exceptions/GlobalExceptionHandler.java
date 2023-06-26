package com.pluralsight.recipe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = EntityWasNotFoundException.class)
	public ResponseEntity<String> entityNotFoundException(EntityWasNotFoundException ex) {
		return new ResponseEntity<String>(ex.getExceptionMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = InvalidParamException.class)
	public ResponseEntity<String> invalidParameterException(InvalidParamException ex) {
		return new ResponseEntity<String>(ex.getExceptionMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
