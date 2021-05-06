package com.example.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.Loan;

public interface ILoanRepository {

	Iterable<Loan> findLoansByUserId(Long userId);

}