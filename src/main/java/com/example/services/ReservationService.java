package com.example.services;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Book;
import com.example.entity.Loan;
import com.example.entity.Reservation;
import com.example.entity.ReservationKey;
import com.example.entity.User;
import com.example.exceptions.CustomCannotReservateLoanAvailableException;
import com.example.exceptions.CustomReservationAlreadyExistsException;
import com.example.exceptions.CustomReservationNotExistsException;
import com.example.exceptions.CustomUserReservationLimitReachedException;
import com.example.repositories.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Transactional
	public void createNewReservation(Book book, User user) throws CustomReservationAlreadyExistsException,
			CustomUserReservationLimitReachedException, CustomCannotReservateLoanAvailableException {

		if (book.canBeLoaned())
			throw new CustomCannotReservateLoanAvailableException();

		if (!user.canReservate())
			throw new CustomUserReservationLimitReachedException();

		if (findReservation(book, user) != null)
			throw new CustomReservationAlreadyExistsException();

		ReservationKey reservationKey = new ReservationKey(user.getId(), book.getId());

		Reservation reservation = new Reservation(user, book, ZonedDateTime.now(), reservationKey);

		reservationRepository.save(reservation);
	}

	@Transactional
	public void deleteReservation(Book book, User user) throws CustomReservationNotExistsException {

		Reservation reservation = findReservation(book, user);

		if (reservation == null)
			throw new CustomReservationNotExistsException();

		reservation.removeReservation();

		reservation = reservationRepository.save(reservation);

		reservationRepository.delete(reservation);

	}

	public List<Reservation> findReservationsByUserId(Integer userId) {

		List<Reservation> reservations = new ArrayList<Reservation>();

		reservationRepository.findReservationsByUserId(userId).forEach(reservations::add);

		return reservations;
	}

	private Reservation findReservation(Book book, User user) {

		ReservationKey reservationKey = new ReservationKey(user.getId(), book.getId());

		Optional<Reservation> optionalReservation = reservationRepository.findById(reservationKey);

		if (optionalReservation.isEmpty())
			return null;

		return optionalReservation.get();

	}

}
