package com.noq.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class User {
    private String userId;
    private String name;
    private Reservation reservation;

    public static User create(String name, Reservation reservation) {
        return new User(
                UUID.randomUUID().toString(),
                name,
                reservation
        );
    }
}
