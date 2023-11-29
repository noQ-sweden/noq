package com.noq.backend.clients.user.booking;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Value
public class CreateBookingRequestDto {
    UUID hostId;
    UUID userId;
    LocalDateTime startDateTime;
}
