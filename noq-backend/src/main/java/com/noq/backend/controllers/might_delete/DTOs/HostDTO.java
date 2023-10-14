package com.noq.backend.controllers.might_delete.DTOs;

import com.noq.backend.models.Address;

public record HostDTO(
        String hostId,
        String name,
        Address address,
        String image
) {
}
