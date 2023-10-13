package com.noq.backend.models;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Data
@Container(containerName = "reservations")
public class Reservation {
    @Id
    private String reservationId;
    @PartitionKey
    private Host host;
    private User user;
    private LocalDateTime reservedTime;
    private Status status;

    @JsonCreator
    public Reservation(
            @JsonProperty("host") Host host,
            @JsonProperty("user") User user,
            @JsonProperty("status") Status status
    ) {
        this.reservationId = UUID.randomUUID().toString();
        this.host = host;
        this.user = user;
        this.status = status;
    }

    public enum Status {
        APPROVED,
        PENDING,
        RESERVED,
        CANCELLED,
        DENIED
    }
}
