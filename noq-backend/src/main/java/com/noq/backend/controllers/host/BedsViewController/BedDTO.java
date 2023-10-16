package com.noq.backend.controllers.host.BedsViewController;

import com.noq.backend.models.Host;

public record BedDTO(
        String id,
        Host host,
        boolean reserved
) {
}
