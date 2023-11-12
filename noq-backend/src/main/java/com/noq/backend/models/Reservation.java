package com.noq.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Reservation {
    private String reservationId;
    private Host host;
    private User user;
    private String reservedTime;
    private Status status;

    public enum Status {
        APPROVED,
        PENDING,
        RESERVED,
        CANCELLED,
        DENIED
    }

    public enum UpdateChangeType {
        UPDATE_STATUS
    }

    public static Reservation create(Host host, User user) {
        return new Reservation(
                UUID.randomUUID().toString(),
                host,
                user,
                LocalDateTime.now().toString(),
                Status.PENDING
        );
    }
}

