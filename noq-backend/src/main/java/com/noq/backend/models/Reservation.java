package com.noq.backend.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonCreator
    public Reservation(@JsonProperty("host") Host host, @JsonProperty("user") User user, @JsonProperty("status") Status status) {
        this.reservationId = UUID.randomUUID().toString();
        this.host = host;
        this.user = user;
        this.status = status;
    }
}
