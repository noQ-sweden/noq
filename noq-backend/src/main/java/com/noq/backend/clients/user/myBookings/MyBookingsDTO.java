package com.noq.backend.clients.user.myBookings;

public record MyBookingsDTO(
        String id,
        BookingDTO[] bookings
) {

    record BookingDTO(
            String id,
            String bookingStatus,
            Host host
    ) {
    }

    record Host(
            String id,
            String name,
            String address1,
            String address2,
            String postalCode,
            String city,
            int countOfAvailablePlaces,
            int totalAvailablePlaces
    ) {
    }
}
