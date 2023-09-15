package com.noq.backend.DTO.cosmos;

import com.noq.backend.models.cosmos.HostCosmos;

public record BedDTO(String id, HostCosmos host, boolean reserved) {
}
