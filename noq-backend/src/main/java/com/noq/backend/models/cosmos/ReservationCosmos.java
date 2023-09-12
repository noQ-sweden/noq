package com.noq.backend.models.cosmos;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.noq.backend.models.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Data
@Container(containerName = "reservations")
public class ReservationCosmos {

    @Id
    private String reservationId;
    @PartitionKey
    private HostCosmos host;
    private UserCosmos user;
    private LocalDateTime reservedTime;
    private Status status;

    @JsonCreator
    public ReservationCosmos(@JsonProperty("host") HostCosmos host, @JsonProperty("user") UserCosmos user, @JsonProperty("status") Status status) {
        this.reservationId = UUID.randomUUID().toString();
        this.host = host;
        this.user = user;
        this.status = status;
    }
}
