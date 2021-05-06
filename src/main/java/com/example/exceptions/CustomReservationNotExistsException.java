package com.example.exceptions;

public class CustomReservationNotExistsException extends Exception {

	private static final long serialVersionUID = -7605662073902578022L;

	public CustomReservationNotExistsException() {
		super();
	}

	public CustomReservationNotExistsException(String message) {
		super(message);
	}
	
	

}
