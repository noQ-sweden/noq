package com.noq.backend.DTO;

import com.noq.backend.models.Reservation;

import java.util.UUID;

public record UserDTO(String id, String name, ReservationDTO reservation) {

}