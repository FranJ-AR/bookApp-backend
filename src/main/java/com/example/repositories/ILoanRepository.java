package com.example.repositories;

import com.example.model.Loan;

public interface ILoanRepository {

	Iterable<Loan> findLoansByUserId(Long userId);

}