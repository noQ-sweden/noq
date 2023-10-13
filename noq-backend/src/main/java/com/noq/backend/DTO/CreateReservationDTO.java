package com.noq.backend.DTO;

public record CreateReservationDTO(
        String hostId,
        String userId,
        String bedId
) {
}
