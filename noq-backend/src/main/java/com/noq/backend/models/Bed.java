package com.noq.backend.models;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Container(containerName = "beds")
public class Bed {
    @Id
    private String id;
    @PartitionKey
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
