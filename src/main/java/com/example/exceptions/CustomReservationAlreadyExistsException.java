package com.example.exceptions;

public class CustomReservationAlreadyExistsException extends Exception {

	private static final long serialVersionUID = -8114556117937342944L;

	public CustomReservationAlreadyExistsException() {
		super();
	}

	public CustomReservationAlreadyExistsException(String message) {
		super(message);
	}

	
}
