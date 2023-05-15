package com.noq.backend.models;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id @Column(name = "id", nullable = false)
    private String id;
    private String street;
    private String city;
    private String region;
    private String zipCode;

    public Address(String street, String city, String region, String zipCode) {
        this.id = UUID.randomUUID().toString();
        this.street = street;
        this.city = city;
        this.region = region;
        this.zipCode = zipCode;
    }
}


