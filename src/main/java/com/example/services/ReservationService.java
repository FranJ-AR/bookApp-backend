package com.example.services;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.BaseLoanAndReservationKey;
import com.example.entity.Book;
import com.example.entity.Reservation;
import com.example.entity.ReservationKey;
import com.example.entity.User;
import com.example.exceptions.CustomReservationAlreadyExistsException;
import com.example.exceptions.CustomReservationNotExistsException;
import com.example.exceptions.CustomUserReservationLimitReachedException;
import com.example.repositories.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Transactional
	public void createNewReservation(Book book, User user) throws
	CustomReservationAlreadyExistsException, CustomUserReservationLimitReachedException {

		if (!user.canReservate())
			throw new CustomUserReservationLimitReachedException();
		
		if( findReservation(book, user) != null) 
			throw new CustomReservationAlreadyExistsException();

		ReservationKey reservationKey = new ReservationKey(user.getId(), book.getId());

		Reservation reservation = new Reservation(user, book, ZonedDateTime.now(), reservationKey);
		
		reservation.add1Reservation();

		reservationRepository.save(reservation);
	}

	@Transactional
	public void deleteReservation(Book book, User user) throws CustomReservationNotExistsException {

		book.remove1Reservation();

		user.remove1Reservation();
				
		Reservation reservation = findReservation(book, user);
				
		if(reservation == null) throw new CustomReservationNotExistsException();
		
		reservation.remove1Reservation();
		
		reservation.removeReservation();

		reservation = reservationRepository.save(reservation);
		
		reservationRepository.delete(reservation);
		
	}
	
	private Reservation findReservation(Book book, User user) {
		
		ReservationKey reservationKey = new ReservationKey(user.getId(), book.getId());
		
		Optional<Reservation> optionalReservation = reservationRepository.findById(reservationKey);
		
		if(optionalReservation.isEmpty()) return null;
		
		return optionalReservation.get();
		
			
	}

}
