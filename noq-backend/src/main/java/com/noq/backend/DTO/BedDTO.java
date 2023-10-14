package com.noq.backend.DTO;

import com.noq.backend.models.Host;

public record BedDTO(
        String id,
        Host host,
        boolean reserved
) {
}
