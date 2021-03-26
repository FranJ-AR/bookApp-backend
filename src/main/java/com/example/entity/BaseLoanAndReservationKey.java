package com.example.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

@Embeddable
@MappedSuperclass
public class BaseLoanAndReservationKey implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7402573353141650645L;

	private Integer userId;
	
	private Integer bookId;

	public BaseLoanAndReservationKey() {
		super();
	}

	public BaseLoanAndReservationKey(Integer userId, Integer bookId) {
		super();
		this.userId = userId;
		this.bookId = bookId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		BaseLoanAndReservationKey other = (BaseLoanAndReservationKey) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
	
	

}
