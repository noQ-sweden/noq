package com.noq.backend.controllers.might_delete.DTOs;

import com.noq.backend.models.Reservation;

public record UserDTO(
        String id,
        String name,
        Reservation reservation
) {
}
