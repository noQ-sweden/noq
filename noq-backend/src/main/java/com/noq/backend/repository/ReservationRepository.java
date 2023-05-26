package com.noq.backend.repository;

import com.noq.backend.models.Reservation;

import java.util.List;

public interface ReservationRepository {

    Reservation save (Reservation reservation);

    Reservation getReservationByReservationId(String reservationId);

    List<Reservation> getAllReservations();


}
