package com.example.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Loan;

public interface ReservationRepository extends CrudRepository<Loan, Integer>{

}
