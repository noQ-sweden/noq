package com.noq.backend.models;
import jakarta.persistence.Column;
import lombok.*;
import jakarta.persistence.Id;

import java.util.*;

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


    @OneToMany(mappedBy = "host",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bed> beds = new ArrayList<>();

    public void addBed(Bed bed) {
        beds.add(bed);
        bed.setHost(this);
    }
}
