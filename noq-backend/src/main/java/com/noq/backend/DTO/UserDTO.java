package com.noq.backend.DTO;

import com.noq.backend.models.Reservation;

public record UserDTO(
        String id,
        String name,
        Reservation reservation
) {
}
