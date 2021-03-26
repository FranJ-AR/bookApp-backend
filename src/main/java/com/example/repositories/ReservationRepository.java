package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Reservation;
import com.example.entity.ReservationKey;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, ReservationKey>{

}
