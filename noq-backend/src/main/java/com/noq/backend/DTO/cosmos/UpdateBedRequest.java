package com.noq.backend.DTO.cosmos;

public record UpdateBedRequest(String bedId, String hostId, boolean reserved) {
}
