package com.example.services;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Book;
import com.example.entity.Loan;
import com.example.entity.LoanKey;
import com.example.entity.User;
import com.example.exceptions.CustomLoanAlreadyExistsException;
import com.example.exceptions.CustomMaximumUserBooksLoanedException;
import com.example.exceptions.CustomNoCopiesForLoanAvailableException;
import com.example.repositories.BookRepository;
import com.example.repositories.LoanRepository;

@Service
public class LoanService {
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Transactional
	public void createNewLoan(Book book, User user) 
			throws CustomNoCopiesForLoanAvailableException, CustomMaximumUserBooksLoanedException, 
			CustomLoanAlreadyExistsException {
		
		if(! book.canBeLoaned()) throw new CustomNoCopiesForLoanAvailableException();
			
		if(! user.canLoan()) throw new CustomMaximumUserBooksLoanedException();
		
		if( findLoan(book, user) != null) throw new CustomLoanAlreadyExistsException();
		
		LoanKey loanKey = new LoanKey(user.getId(), book.getId());
			
		Loan loan = new Loan(user, book, ZonedDateTime.now(), loanKey, false, Loan.calculateDateReturn());
		
		loan.add1Loan();
		
		loanRepository.save(loan);
				
	}
	
	private Loan findLoan(Book book, User user) {
		
		LoanKey loanKey = new LoanKey(user.getId(), book.getId());
		
		Optional<Loan> optionalLoan = loanRepository.findById(loanKey);
		
		if(optionalLoan.isEmpty()) return null;
		
		return optionalLoan.get();
	}
	

}
