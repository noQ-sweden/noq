package com.noq.backend.models;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Data
@Container(containerName = "users")
public class User {
    @Id
    @PartitionKey
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
