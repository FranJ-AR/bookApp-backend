package com.example.exceptions;

public class CustomLoanAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 3433157218865525536L;

	public CustomLoanAlreadyExistsException() {
		super();
	}

	public CustomLoanAlreadyExistsException(String message) {
		super(message);
	}
	
	

}
