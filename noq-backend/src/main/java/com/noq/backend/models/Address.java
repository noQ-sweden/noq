package com.noq.backend.models;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.util.UUID;

@Data
@Entity(name="address")
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id @Column(name = "id", nullable = false)
    private String id;
    private String street;
    private String streetNum;
    private String postalCode;
    private String cityName;
}


