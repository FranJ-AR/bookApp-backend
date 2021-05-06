package com.example.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.Reservation;

public interface IReservationRepository {

	Iterable<Reservation> findReservationsByUserId(Long userId);

}