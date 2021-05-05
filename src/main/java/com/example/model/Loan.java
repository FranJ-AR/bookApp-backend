package com.example.model;

import java.time.ZonedDateTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class Loan extends BaseLoanAndReservation{
	
	public static final Integer DAYS_TO_RETURN = 15;
	
	public static final Integer DAYS_TO_PICK_UP = 2;
	
	@EmbeddedId
	private LoanKey loanKey;
		
	private ZonedDateTime maximumReturnDate;
	
	private ZonedDateTime maximumPickedUpDate;

	public Loan() {
		super();
	}
	
	public Loan(User user, Book book, ZonedDateTime timestamp, LoanKey loanKey, 
			 ZonedDateTime maximumReturnDate, ZonedDateTime maximumPickedUpDate) {
		super(user, book, timestamp);
		this.loanKey = loanKey;
		this.maximumReturnDate = maximumReturnDate;
		this.maximumPickedUpDate = maximumPickedUpDate;
		
	}

	public LoanKey getLoanKey() {
		return loanKey;
	}

	public void setLoanKey(LoanKey loanKey) {
		this.loanKey = loanKey;
	}

	public boolean isBeenPickedUp() {
		return maximumPickedUpDate == null;
	}

	public ZonedDateTime getMaximumReturnDate() {
		return maximumReturnDate;
	}

	public void setMaximumReturnDate(ZonedDateTime maximumReturnDate) {
		this.maximumReturnDate = maximumReturnDate;
	}

	public ZonedDateTime getMaximumPickedUpDate() {
		return maximumPickedUpDate;
	}

	public void setMaximumPickedUpDate(ZonedDateTime maximumPickedUpDate) {
		this.maximumPickedUpDate = maximumPickedUpDate;
	}
	
public static ZonedDateTime calculateDateReturn() {
		
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		
		zonedDateTime = zonedDateTime.plusDays(DAYS_TO_RETURN)
				.withHour(21)
				.withMinute(59)
				.withSecond(59);
		
		return zonedDateTime;
	}
	
	public static ZonedDateTime calculateMaximumDatePickedUp() {
		
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		
		zonedDateTime = zonedDateTime.plusDays(DAYS_TO_PICK_UP)
				.withHour(21)
				.withMinute(59)
				.withSecond(59);
		
		return zonedDateTime;
			
	}
	
	
}
