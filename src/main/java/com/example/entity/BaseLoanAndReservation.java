package com.example.entity;

import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.MapsId;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
public abstract class BaseLoanAndReservation {
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@MapsId("userId")
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@MapsId("bookId")
	@JoinColumn(name="book_id")
	private Book book;
	
	@Column(name="generated_on")
	private ZonedDateTime timestamp;
	
	public BaseLoanAndReservation() {
		super();
	}

	public BaseLoanAndReservation(User user, Book book, ZonedDateTime timestamp) {
		super();
		this.user = user;
		this.book = book;
		this.timestamp = timestamp;
	}

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
