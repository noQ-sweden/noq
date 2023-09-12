package com.noq.backend.models.cosmos;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.noq.backend.models.Address;
import com.noq.backend.models.cosmos.Bed;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@Container(containerName = "hosts")
public class HostCosmos {

    @Id
    @PartitionKey
    private String hostId;
    private String name;
    private Address address;
    private String image;
    private List<Bed> beds = new ArrayList<>();

    public HostCosmos( String name, Address address, String image) {
        this.hostId = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.image = image;
        this.beds = new ArrayList<>();
    }

   // public void addBed(Bed bed) {
   //     beds.add(bed);
   //     bed.setHost(this);
   // }

}