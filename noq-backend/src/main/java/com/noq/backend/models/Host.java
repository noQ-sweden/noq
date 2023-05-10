package com.noq.backend.models;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import java.util.Set;
import java.util.UUID;
import jakarta.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@Entity(name="host")
public class Host {
    @Id private UUID hostId;
    private String name;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "host",  cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UUID> bedIds = new HashSet<>();

}
