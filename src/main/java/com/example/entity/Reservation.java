package com.example.entity;

import java.time.ZonedDateTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Reservation extends BaseLoanAndReservation {

	@EmbeddedId
	private ReservationKey reservationKey;

	public Reservation() {
		super();
	}

	public Reservation(User user, Book book, ZonedDateTime timestamp, ReservationKey reservationKey) {
		super(user, book, timestamp);
		this.reservationKey = reservationKey;

	}

	public ReservationKey getReservationKey() {
		return reservationKey;
	}

	public void setReservationKey(ReservationKey reservationKey) {
		this.reservationKey = reservationKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reservationKey == null) ? 0 : reservationKey.hashCode());
		return result;
	}

	public void add1Reservation() {

		getBook().add1Reservation();
		getUser().add1Reservation();
	}
	
	public void remove1Reservation() {
		
		getBook().remove1Reservation();
		getUser().remove1Reservation();
	}

	// A reservation is equal to another one if the id of the books and the users
	// are the same
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		if (reservationKey == null) {
			if (other.reservationKey != null)
				return false;
		} else if (!reservationKey.equals(other.reservationKey))
			return false;
		return true;
	}

}
