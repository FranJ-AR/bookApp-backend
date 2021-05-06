package com.example.repositories;

import com.example.model.Reservation;

public interface IReservationRepository {

	Iterable<Reservation> findReservationsByUserId(Long userId);

}