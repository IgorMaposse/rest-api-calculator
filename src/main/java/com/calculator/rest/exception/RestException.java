package com.calculator.rest.exception;

import org.springframework.http.HttpStatus;

public class RestException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	private String message;
	
	public RestException(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
}
