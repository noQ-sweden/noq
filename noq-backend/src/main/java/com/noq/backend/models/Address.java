package com.noq.backend.models;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.util.UUID;

@Data
@Embedded
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id @Column(name = "id", nullable = false)
    private UUID id;
    private String street;
    private String city;
    private String region;
    private String zipCode;
}


