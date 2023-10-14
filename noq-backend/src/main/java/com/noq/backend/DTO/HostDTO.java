package com.noq.backend.DTO;

import com.noq.backend.models.Address;

public record HostDTO(
        String hostId,
        String name,
        Address address,
        String image
) {
}
