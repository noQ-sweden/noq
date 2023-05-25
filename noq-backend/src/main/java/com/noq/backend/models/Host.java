package com.noq.backend.models;
import jakarta.persistence.Column;
import lombok.*;
import jakarta.persistence.Id;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="hosts")
public class Host {

    @Id @Column(name = "id", nullable = false)
    private String hostId;
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Address address;
    private String image;
    private Long bed;

    /*
    @OneToMany(mappedBy = "host",  cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bed> beds = new HashSet<>();
*/

}
