package com.example.services;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exceptions.CustomLoanAlreadyExistsException;
import com.example.exceptions.CustomMaximumUserBooksLoanedException;
import com.example.exceptions.CustomNoCopiesForLoanAvailableException;
import com.example.model.Book;
import com.example.model.Loan;
import com.example.model.LoanKey;
import com.example.model.User;
import com.example.repositories.LoanRepositoryImpl;

@Service
public class LoanServiceImpl implements LoanService {
	
	@Autowired
	private LoanRepositoryImpl loanRepositoryImpl;
	
	@Override
	@Transactional
	public void createNewLoan(Book book, User user) 
			throws CustomNoCopiesForLoanAvailableException, CustomMaximumUserBooksLoanedException, 
			CustomLoanAlreadyExistsException {
		
		if(! book.canBeLoaned()) throw new CustomNoCopiesForLoanAvailableException();
			
		if(! user.canLoan()) throw new CustomMaximumUserBooksLoanedException();
		
		if( findLoan(book, user) != null) throw new CustomLoanAlreadyExistsException();
		
		LoanKey loanKey = new LoanKey(user.getId(), book.getId());
			
		Loan loan = new Loan(user, book, ZonedDateTime.now(), loanKey, 
				Loan.calculateDateReturn(),
				Loan.calculateMaximumDatePickedUp());
		
		loanRepositoryImpl.save(loan);
				
	}
	
	@Override
	public List<Loan> findLoansByUserId(Long userId){
		
		List<Loan> loans = new ArrayList<Loan>();
		
		loanRepositoryImpl.findLoansByUserId(userId).forEach(loans::add);
		
		return loans;
	}
	
	private Loan findLoan(Book book, User user) {
		
		LoanKey loanKey = new LoanKey(user.getId(), book.getId());
		
		Optional<Loan> optionalLoan = loanRepositoryImpl.findById(loanKey);
		
		if(optionalLoan.isEmpty()) return null;
		
		return optionalLoan.get();
	}
	
	

}
