package com.noq.backend.models;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Data
@Entity(name="address")
public class Address {
    @Id @Column(name = "id", nullable = false)
    private Long id;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
}
