package com.noq.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.FetchType;

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
