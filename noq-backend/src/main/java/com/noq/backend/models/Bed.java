package com.noq.backend.models;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity(name ="bads")
public class Bed {
    @Id @Column(name = "id", nullable = false)
    private Long id;
    private int size;
    private double price;

    public Bed(int size, double price) {
        this.id= UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.size = size;
        this.price = price;
    }

}
