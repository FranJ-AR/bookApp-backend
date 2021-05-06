package com.example.services;

import java.util.List;

import com.example.exceptions.CustomCannotReservateLoanAvailableException;
import com.example.exceptions.CustomReservationAlreadyExistsException;
import com.example.exceptions.CustomReservationNotExistsException;
import com.example.exceptions.CustomUserReservationLimitReachedException;
import com.example.model.Book;
import com.example.model.Reservation;
import com.example.model.User;

public interface ReservationService {

	void createNewReservation(Book book, User user) throws CustomReservationAlreadyExistsException,
			CustomUserReservationLimitReachedException, CustomCannotReservateLoanAvailableException;

	void deleteReservation(Book book, User user) throws CustomReservationNotExistsException;

	List<Reservation> findReservationsByUserId(Long long1);

}