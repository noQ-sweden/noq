package com.noq.backend.DTO;

import com.noq.backend.models.Status;

import java.util.List;

public record RequestsViewDTO(List<Reservation> reservations) {

    public record Reservation(String reservationId, Status status, User user){}

    public record User(String id, String name) {
    }
}
