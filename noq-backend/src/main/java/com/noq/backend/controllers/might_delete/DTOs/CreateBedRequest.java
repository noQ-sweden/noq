package com.noq.backend.controllers.might_delete.DTOs;

public record CreateBedRequest(
        String hostId,
        int numberOfBeds
) {
}
