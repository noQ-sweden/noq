package com.noq.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Bed {
    private String id;
    private String hostId;
    private Host host;
    private boolean reserved;

    public static Bed create(Host host, String hostId, boolean isReserved) {
        return new Bed(
                UUID.randomUUID().toString(),
                hostId,
                host,
                isReserved
        );
    }

}
