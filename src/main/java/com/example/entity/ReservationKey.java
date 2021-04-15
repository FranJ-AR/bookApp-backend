package com.example.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Embeddable
@JsonIgnoreProperties({"bookId","userId"})
public class ReservationKey extends BaseLoanAndReservationKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6796514200541500480L;

	public ReservationKey() {
		super();
	}

	public ReservationKey(Integer userId, Integer bookId) {
		super(userId, bookId);
	}
	
	
	
	
	
	

}
