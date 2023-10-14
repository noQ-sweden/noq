package com.noq.backend.models;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Container(containerName = "hosts")
public class Host {
    @Id
    @PartitionKey
    private String hostId;
    private String name;
    private Address address;
    private String image;

    public Host(String name, Address address, String image) {
        this.hostId = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.image = image;
    }
}
