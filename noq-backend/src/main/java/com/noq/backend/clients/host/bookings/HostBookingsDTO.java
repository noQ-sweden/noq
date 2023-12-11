package com.noq.backend.clients.host.bookings;

public record HostBookingsDTO(
        String id,
        BookingDTO[] bookings
) {

    record BookingDTO(
            String id,
            String bookingStatus
    ) {
    }
}
