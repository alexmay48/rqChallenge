package com.example.rqchallenge.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Catch the ApiException so we can send it back to the user in a response so
	 * that they have a more detailed understanding of why the 3rd party failed.
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<String> handleApiException(ApiException exception) {
		return ResponseEntity.status(exception.status).body(exception.getMessage());
	}

}
