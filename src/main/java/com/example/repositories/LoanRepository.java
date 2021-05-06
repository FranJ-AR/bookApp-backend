package com.example.repositories;

import com.example.model.Loan;

public interface LoanRepository {

	Iterable<Loan> findLoansByUserId(Long userId);

}