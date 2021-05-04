package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Embeddable
@JsonIgnoreProperties({"bookId","userId"})
public class LoanKey extends BaseLoanAndReservationKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4082531323931999312L;

	public LoanKey() {
		super();
	}

	public LoanKey(Long userId, Long bookId) {
		super(userId, bookId);
	}
	
	

}
