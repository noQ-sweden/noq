package com.noq.backend.DTO;

import com.noq.backend.models.Status;

public record RequestsViewDTO(ReservationDTO reservation) {

    public record ReservationDTO(String reservationId, Status status, UserDTO user){}

    public record UserDTO(String id, String name) {
    }
}
