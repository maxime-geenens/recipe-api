package com.pluralsight.recipe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = EntityNotFoundException.class)
	public ResponseEntity<String> entityNotFoundException(EntityNotFoundException ex) {
		return new ResponseEntity<String>(ex.getExceptionMessage(), HttpStatus.NOT_FOUND);
	}
}
