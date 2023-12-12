package com.noq.backend.clients.host.bookings;

import lombok.Builder;

public record BookingsPageDTO(
        String id,
        BookingDTO[] approvedBookings,
        BookingDTO[] disapprovedBookings,
        BookingDTO[] pendingBookings
) {
    @Builder
    public BookingsPageDTO {
    }
    public record BookingDTO(
            String id,
            String userId,
            String name,
            String unoCode,
            int queuingPlace,
            String status
    ) {
        @Builder
        public BookingDTO {
        }
    }
}
