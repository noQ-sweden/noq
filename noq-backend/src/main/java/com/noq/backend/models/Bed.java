package com.noq.backend.models;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity(name ="bed")
public class Bed {
    @Id @Column(name = "id", nullable = false)
    private UUID bedId;
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Host host;

}
