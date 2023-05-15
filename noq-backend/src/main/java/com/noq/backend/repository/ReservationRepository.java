package com.noq.backend.repository;

import com.noq.backend.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {

    Reservation getReservationByUserId(String userId);
}
