package com.example.services;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exceptions.CustomCannotReservateLoanAvailableException;
import com.example.exceptions.CustomReservationAlreadyExistsException;
import com.example.exceptions.CustomReservationNotExistsException;
import com.example.exceptions.CustomUserReservationLimitReachedException;
import com.example.model.Book;
import com.example.model.Reservation;
import com.example.model.ReservationKey;
import com.example.model.User;
import com.example.repositories.ReservationRepositoryImp;

@Service
public class ReservationServiceImp implements IReservationService {

	@Autowired
	private ReservationRepositoryImp reservationRepositoryImp;

	@Override
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

		reservationRepositoryImp.save(reservation);
	}

	@Override
	@Transactional
	public void deleteReservation(Book book, User user) throws CustomReservationNotExistsException {

		Reservation reservation = findReservation(book, user);

		if (reservation == null)
			throw new CustomReservationNotExistsException();

		reservation.removeReservation();

		reservation = reservationRepositoryImp.save(reservation);

		reservationRepositoryImp.delete(reservation);

	}

	@Override
	public List<Reservation> findReservationsByUserId(Long long1) {

		List<Reservation> reservations = new ArrayList<Reservation>();

		reservationRepositoryImp.findReservationsByUserId(long1).forEach(reservations::add);

		return reservations;
	}

	private Reservation findReservation(Book book, User user) {

		ReservationKey reservationKey = new ReservationKey(user.getId(), book.getId());

		Optional<Reservation> optionalReservation = reservationRepositoryImp.findById(reservationKey);

		if (optionalReservation.isEmpty())
			return null;

		return optionalReservation.get();

	}

}
