package com.example.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

@Embeddable
@MappedSuperclass
public class BaseLoanReservationKey implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7402573353141650645L;

	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "book_id")
	private Integer bookId;

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
	
	

}
