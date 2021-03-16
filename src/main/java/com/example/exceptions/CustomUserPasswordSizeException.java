package com.example.exceptions;

public class CustomUserPasswordSizeException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 460508399033086701L;

	public CustomUserPasswordSizeException(String message) {
		
		super(message);
	}

}
