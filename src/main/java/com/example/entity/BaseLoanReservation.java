package com.example.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

public abstract class BaseLoanReservation {
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Book book;
	
	@Column(name="generated_on")
	private ZonedDateTime timestamp;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(ZonedDateTime timestamp) {
		this.timestamp = timestamp;
	}

	

}
