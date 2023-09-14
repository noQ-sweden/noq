package com.noq.backend.DTO.cosmos;

import com.noq.backend.models.Status;

public record RequestsViewCosmosDTO(Reservation reservation) {
    public record Reservation(String reservationId, Status status, User user){}
    public record User(String id, String name) {
    }
}
