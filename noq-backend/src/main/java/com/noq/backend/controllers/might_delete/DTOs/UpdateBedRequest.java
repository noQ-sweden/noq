package com.noq.backend.controllers.might_delete.DTOs;

public record UpdateBedRequest(
        String bedId,
        String hostId,
        boolean reserved
) {
}
