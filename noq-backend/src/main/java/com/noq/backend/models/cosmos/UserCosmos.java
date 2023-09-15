package com.noq.backend.models.cosmos;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@NoArgsConstructor
@Data
@Container(containerName = "users")
public class UserCosmos {

    @Id
    @PartitionKey
    private String id;
    private String name;
    private ReservationCosmos reservation;

    public UserCosmos(String name, ReservationCosmos reservation) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.reservation = reservation;
    }
}
