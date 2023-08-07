package com.noq.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@NoArgsConstructor
@Data
@AllArgsConstructor
public class Reservation {

    private String reservationId;

    private Host host;

    private User user;

    private LocalDateTime reservedTime;
    private Status status;

    public Reservation(Host host, User user, Status status) {
        this.reservationId = UUID.randomUUID().toString();
        this.host = host;
        this.user = user;
        this.status = status;
    }
}
