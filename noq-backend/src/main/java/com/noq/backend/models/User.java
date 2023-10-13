package com.noq.backend.models;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@NoArgsConstructor
@Data
@Container(containerName = "users")
public class User {
    @Id
    @PartitionKey
    private String id;
    private String name;
    private Reservation reservation;

    public User(String name, Reservation reservation) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.reservation = reservation;
    }
}
