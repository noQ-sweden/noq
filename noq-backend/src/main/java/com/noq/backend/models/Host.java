package com.noq.backend.models;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity(name="hosts")
public class Host {
    @Id private Long hostId;
    private String name;
    private Address location;

    @OneToMany(mappedBy = "host",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bed> beds = new ArrayList<>();


}
