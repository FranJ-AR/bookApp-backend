package com.example.demo;

public class ErrorMessages {
	
	private String error;
	
	public static final ErrorMessages USER_ALREADY_EXISTS = 
			new ErrorMessages("User already exists");
	
	public static final ErrorMessages INVALID_OR_EXPIRED_TOKEN_OR_USER_NOT_FOUND = 
			new ErrorMessages("Invalid or expired token or user not found");
	
	public static final ErrorMessages USERNAME_NOT_FOUND = 
			new ErrorMessages("Username not found");
	
	public static final ErrorMessages INCORRECT_PASSWORD = 
			new ErrorMessages("Incorrect password");
	
	public static final ErrorMessages INVALID_CREDENTIALS = 
			new ErrorMessages("Invalid credentials");
	
	public static final ErrorMessages SYSTEM_ERROR = 
			new ErrorMessages("An unknown error happened, try again later");
	
	public static final ErrorMessages SIZE_USERNAME_PASSWORD = 
			new ErrorMessages("Username and password must have at least 5 characters");
	
	public static final ErrorMessages WRONG_PARAMETERS = 
			new ErrorMessages("Wrong parameters");
	
	public static final ErrorMessages PARAMETERS_REQUIRED = 
			new ErrorMessages("Parameters required");
	
	public static final ErrorMessages SIZE_AUTHOR = 
			new ErrorMessages("Please, insert at least one letter");
	
	
	private ErrorMessages(String error) {
		
		this.error = error;
		
	}

	public String getErrorMessage() {
		return error;
	}

	public void setErrorMessage(String error) {
		this.error = error;
	}
	
	
}
