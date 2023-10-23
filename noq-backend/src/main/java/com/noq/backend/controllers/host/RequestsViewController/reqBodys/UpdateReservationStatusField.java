package com.noq.backend.controllers.host.RequestsViewController.reqBodys;

import com.noq.backend.models.Reservation;

public record UpdateReservationStatusField(
        String reservationId,
        String newValue,
        Reservation.UpdateChangeType updateChangeType
) {
}
