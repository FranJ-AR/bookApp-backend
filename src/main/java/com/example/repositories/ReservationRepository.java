package com.example.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Reservation;
import com.example.model.ReservationKey;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, ReservationKey>{
	
	@Query("SELECT reservation FROM Reservation reservation WHERE :userId = user_id")
	public Iterable<Reservation> findReservationsByUserId(@Param("userId") Long userId);

}
