package com.noq.backend.models;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import java.util.Set;
import java.util.UUID;
import jakarta.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="hosts")
public class Host {
    @Id @Column(name = "id", nullable = false)
    private Long hostId;
    private String name;
    private Address location; // Should this be a ManyToMany relationship?

    @OneToMany(mappedBy = "host",  cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bed> beds = new HashSet<>();


}
