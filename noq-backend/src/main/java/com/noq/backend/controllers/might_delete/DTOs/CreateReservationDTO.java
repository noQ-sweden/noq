package com.noq.backend.controllers.might_delete.DTOs;

public record CreateReservationDTO(
        String hostId,
        String userId,
        String bedId
) {
}
