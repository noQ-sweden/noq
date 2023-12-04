package com.noq.backend.clients.user.booking;

public record UserBookingPageDTO(
        String userId,
        String hostId,
        String name,
        String address1,
        String address2,
        String postalCode,
        String city,
        int countOfAvailablePlaces,
        int totalAvailablePlaces
) {
}
