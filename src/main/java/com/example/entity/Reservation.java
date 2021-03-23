package com.example.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Reservation extends BaseLoanReservation{
	
	@EmbeddedId
	private ReservationKey reservationKey;

	public Reservation() {
		super();
	}

	public ReservationKey getReservationKey() {
		return reservationKey;
	}

	public void setReservationKey(ReservationKey reservationKey) {
		this.reservationKey = reservationKey;
	}
	
	

}
