package com.noq.backend.models;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Container(containerName = "beds")
public class Bed {

    @Id
    private String bedId;
    @PartitionKey
    private HostCosmos host;
    private Boolean reserved;

    public Bed(HostCosmos host){
        this.bedId = UUID.randomUUID().toString();
        this.host = host;
        this.reserved = false;
    }

}
