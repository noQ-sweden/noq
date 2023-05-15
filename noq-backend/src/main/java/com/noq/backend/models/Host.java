package com.noq.backend.models;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import java.util.UUID;
import jakarta.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@Entity(name="host")
public class Host {
    @Id private String hostId;
    private String name;

    @OneToOne()
    private Address address;

    @OneToMany(mappedBy = "bed",  cascade = CascadeType.ALL, orphanRemoval = true)
    private HashSet<Bed> bedIds = new HashSet<>();

    public Host(String name, Address address, HashSet<Bed> beds) {
        this.hostId = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.bedIds = beds;
    }
}
