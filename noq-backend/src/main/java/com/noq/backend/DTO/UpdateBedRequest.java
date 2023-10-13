package com.noq.backend.DTO;

public record UpdateBedRequest(
        String bedId,
        String hostId,
        boolean reserved
) {
}
