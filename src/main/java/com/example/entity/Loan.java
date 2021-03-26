package com.example.entity;

import java.time.ZonedDateTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Loan extends BaseLoanAndReservation{
	
	public static final Integer DAYS_TO_RETURN = 15;
	
	@EmbeddedId
	private LoanKey loanKey;
	
	private Boolean beenPickedUp;
	
	private ZonedDateTime maximumReturnDate;

	public Loan() {
		super();
	}
	
	public Loan(User user, Book book, ZonedDateTime timestamp, LoanKey loanKey, 
			boolean beenPickedUp, ZonedDateTime maximumReturnDate) {
		super(user, book, timestamp);
		this.loanKey = loanKey;
		this.beenPickedUp = beenPickedUp;
		this.maximumReturnDate = maximumReturnDate;
		
	}

	public LoanKey getLoanKey() {
		return loanKey;
	}

	public void setLoanKey(LoanKey loanKey) {
		this.loanKey = loanKey;
	}

	public boolean isBeenPickedUp() {
		return beenPickedUp;
	}

	public void setBeenPickedUp(boolean beenPickedUp) {
		this.beenPickedUp = beenPickedUp;
	}

	public ZonedDateTime getMaximumReturnDate() {
		return maximumReturnDate;
	}

	public void setMaximumReturnDate(ZonedDateTime maximumReturnDate) {
		this.maximumReturnDate = maximumReturnDate;
	}
	
	public static ZonedDateTime calculateDateReturn() {
		
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		
		zonedDateTime = zonedDateTime.plusDays(DAYS_TO_RETURN)
				.withHour(23)
				.withMinute(59)
				.withSecond(59);
		
		return zonedDateTime;
	}
	

}
