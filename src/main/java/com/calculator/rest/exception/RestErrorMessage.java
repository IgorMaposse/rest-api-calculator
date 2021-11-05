package com.calculator.rest.exception;

public class RestErrorMessage {
	
	private int statusCode;
	private String reasonPhrase;
	private String message;
	
	public RestErrorMessage(int statusCode, String reasonPhrase, String message) {
		this.statusCode = statusCode;
		this.reasonPhrase = reasonPhrase;
		this.message = message;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	public String getReasonPhrase() {
		return reasonPhrase;
	}
	public String getMessage() {
		return message;
	}
}
