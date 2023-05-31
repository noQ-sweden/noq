package com.noq.backend.repository;

import com.noq.backend.models.Host;
import com.noq.backend.models.Reservation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class ReservationRepositoryImpl implements ReservationRepository {
   
    private final Map<String, Reservation> reservations = new HashMap<>();

    @Override
    public Reservation save(Reservation reservation) {
        return reservations.put(reservation.getReservationId(), reservation);
    }
    @Override
    public void saveAll(List<Reservation> reservations){
        for (Reservation reservation : reservations) {
            this.reservations.put(reservation.getReservationId(), reservation);
        }
    };

    @Override
    public Reservation getReservationByReservationId(String reservationId) {
        return reservations.get(reservationId);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations.values());
    }


}
