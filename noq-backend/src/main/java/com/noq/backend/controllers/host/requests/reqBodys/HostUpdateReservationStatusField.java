package com.noq.backend.controllers.host.requests.reqBodys;

import com.noq.backend.models.Reservation;

public record HostUpdateReservationStatusField(
        String reservationId,
        String newValue,
        Reservation.UpdateChangeType updateChangeType
) {
}
