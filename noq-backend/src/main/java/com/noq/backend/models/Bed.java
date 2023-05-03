package com.noq.backend.models;

import jakarta.persistence;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity(name ="bads")
public class Bed {
    @Id @Column(name = "id", nullable = false)
    private Long id;
    private int size;
    private BigDecimal price;
    private Status status;
    private Address address;
    private String picture;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Host host;

}
