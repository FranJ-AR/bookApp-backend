package com.example.exceptions;

import java.time.ZonedDateTime;

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
	
	public static final ErrorMessages BOOK_ID_NOT_EXISTS = 
			new ErrorMessages("The id of the book does not exist");
	
	public static final ErrorMessages NO_COPIES_FOR_LOAN = 
			new ErrorMessages("No copies for loan left");
	
	public static final ErrorMessages USER_LIMIT_LOAN_REACHED = 
			new ErrorMessages("User limit loan reached");
	
	public static final ErrorMessages LOAN_ALREADY_EXISTS = 
			new ErrorMessages("Loan already exists");
	
	public static final ErrorMessages UNKNOWN_ERROR_LOAN = 
			new ErrorMessages("Unknown error loan");
	
	public static final ErrorMessages RESERVATION_NOT_EXISTS = 
			new ErrorMessages("The reservation does not exist");
	
	public static final ErrorMessages UNKNOWN_ERROR_RESERVATION = 
			new ErrorMessages("The reservation does not exist");
	
	public static final ErrorMessages RESERVATION_ALREADY_EXISTS = 
			new ErrorMessages("Reservation already exists");
	
	public static final ErrorMessages RESERVATION_LIMIT_REACHED = 
			new ErrorMessages("User limit reservation reached");
	
	public static final ErrorMessages RESERVATION_UNAVAILABLE_LOAN_AVAILABLE = 
			new ErrorMessages("Cannot reservate, loan is available");

	
	private ErrorMessages(String error) {
		
		this.error = error;
		
	}

	public String getErrorMessage() {
		return error;
	}

	public void setErrorMessage(String error) {
		this.error = error;
	}
	
	public ZonedDateTime getDateTime() {
		
		return ZonedDateTime.now();
		
		
	}
	
	
}
