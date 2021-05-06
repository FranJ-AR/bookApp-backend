package com.example.repositories;

import com.example.model.Reservation;

public interface ReservationRepository {

	Iterable<Reservation> findReservationsByUserId(Long userId);

}