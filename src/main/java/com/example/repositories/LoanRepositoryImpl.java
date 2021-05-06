package com.example.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Loan;
import com.example.model.LoanKey;

@Repository
public interface LoanRepositoryImpl extends CrudRepository<Loan,LoanKey>, LoanRepository {
		
	@Query("SELECT loan FROM Loan loan WHERE :userId = user_id")
	public Iterable<Loan> findLoansByUserId(@Param("userId") Long userId);
	

}
