package com.example.rqchallenge.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * We can throw this exception from the 3rd Party Api to make sure that we can
 * catch it and pass it along back to the caller to our service.
 * 
 * @author alexmay
 *
 */
public class ApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7559319926081250378L;

	@Getter
	@Setter
	HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

	public ApiException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}

}
