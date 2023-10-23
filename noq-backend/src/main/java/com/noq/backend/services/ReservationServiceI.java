package com.noq.backend.services;

import com.noq.backend.models.Reservation;
import reactor.core.publisher.Mono;

public interface ReservationServiceI {
    Mono<Reservation> updateReservationField(String reservationId, String newValue, Reservation.UpdateChangeType updateChangeType);
}
