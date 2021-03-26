package com.example.exceptions;

public class CustomNoCopiesForLoanAvailableException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 929136953891294736L;

	public CustomNoCopiesForLoanAvailableException() {
		super("The book cannot be loaned, all copies have been loaned");
	}

	public CustomNoCopiesForLoanAvailableException(String message) {
		super(message);
	}
	
	

}
