package com.noq.backend.models;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity(name ="beds")
public class Bed {
    @Id @Column(name = "id", nullable = false)
    private Long id;
    private int size;
    private BigDecimal price;
    private Status status;
    private Address address; // Should this be a ManyToMany relationship with Address?
    private String picture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Host host;

}
