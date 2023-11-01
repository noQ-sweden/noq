package com.noq.backend.controllers.host.bookings;

public record HostBookingsPageDTO(
        String id,
        Reservation[] reservations
) {
    record Reservation(
            String id,
            String name,
            int queuingPlace,
            com.noq.backend.models.Reservation.Status status
    ) {
    }
}
