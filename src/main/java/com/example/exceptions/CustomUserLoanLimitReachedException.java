package com.example.exceptions;

public class CustomUserLoanLimitReachedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1573716569187812038L;

	public CustomUserLoanLimitReachedException() {
		super();
	}

	public CustomUserLoanLimitReachedException(String message) {
		super(message);
	}
	
	

}
