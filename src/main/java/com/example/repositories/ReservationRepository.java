package com.example.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Loan;
import com.example.entity.Reservation;
import com.example.entity.ReservationKey;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, ReservationKey>{
	
	@Query("SELECT reservation FROM Reservation reservation WHERE :userId = user_id")
	public Iterable<Reservation> findReservationsByUserId(@Param("userId") Integer userId);

}
