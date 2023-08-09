package com.noq.backend.DTO;

public record RequestsViewDTO(String reservationId, UserDTO user) {

    public record UserDTO(String id, String name) {
    }
}
