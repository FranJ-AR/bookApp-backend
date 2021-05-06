package com.example.services;

import java.util.List;

import com.example.exceptions.CustomLoanAlreadyExistsException;
import com.example.exceptions.CustomMaximumUserBooksLoanedException;
import com.example.exceptions.CustomNoCopiesForLoanAvailableException;
import com.example.model.Book;
import com.example.model.Loan;
import com.example.model.User;

public interface ILoanService {

	void createNewLoan(Book book, User user) throws CustomNoCopiesForLoanAvailableException,
			CustomMaximumUserBooksLoanedException, CustomLoanAlreadyExistsException;

	List<Loan> findLoansByUserId(Long userId);

}