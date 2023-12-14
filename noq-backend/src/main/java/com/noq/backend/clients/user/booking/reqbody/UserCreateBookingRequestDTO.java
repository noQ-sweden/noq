package com.noq.backend.clients.user.booking.reqbody;

public record UserCreateBookingRequestDTO(
        String userId,
        String hostId
) {
}
