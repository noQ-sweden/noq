package com.noq.backend.DTO;

public record RequestsViewDTO(String reservationId, UserDTO userDTO) {

    public record UserDTO(String id, String name) {
    }
}
