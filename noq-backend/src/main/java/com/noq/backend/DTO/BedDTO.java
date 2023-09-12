package com.noq.backend.DTO;

import com.noq.backend.models.cosmos.HostCosmos;

public record BedDTO(String id, HostCosmos host, boolean reserved) {
}
