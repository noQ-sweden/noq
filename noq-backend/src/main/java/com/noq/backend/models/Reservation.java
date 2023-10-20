package com.noq.backend.models;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Container(containerName = "reservations")
public class Reservation {
    @Id
    @PartitionKey
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
        ACCEPT,
        DENY
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

