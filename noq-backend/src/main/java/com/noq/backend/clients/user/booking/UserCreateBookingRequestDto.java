package com.noq.backend.clients.user.booking;

import java.util.UUID;

//@Builder
//@Value
public record UserCreateBookingRequestDto(
        UUID hostId,
        UUID userId
//        LocalDateTime startDateTime
) {
}
