package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Loan;
import com.example.entity.LoanKey;

@Repository
public interface LoanRepository extends CrudRepository<Loan,LoanKey> {

}
