package com.example.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Loan extends BaseLoanReservation{
	
	@EmbeddedId
	private LoanKey loanKey;

	public Loan() {
		super();
	}

	public LoanKey getLoanKey() {
		return loanKey;
	}

	public void setLoanKey(LoanKey loanKey) {
		this.loanKey = loanKey;
	}
	
	
	
	

}
