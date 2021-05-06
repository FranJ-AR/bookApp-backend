package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Reservation;
import com.example.model.ReservationKey;

@Repository
public interface ReservationRepositoryImp extends CrudRepository<Reservation, ReservationKey>, IReservationRepository{

}
