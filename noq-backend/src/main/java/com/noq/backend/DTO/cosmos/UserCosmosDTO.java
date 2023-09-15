package com.noq.backend.DTO.cosmos;

import com.noq.backend.models.cosmos.ReservationCosmos;

public record UserCosmosDTO(String id, String name, ReservationCosmos reservation) {
}
