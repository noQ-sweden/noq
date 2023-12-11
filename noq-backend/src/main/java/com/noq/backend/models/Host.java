package com.noq.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Value
@Builder(toBuilder = true)
@Entity(name = "HOST")
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID hostId;
    String name;
    String address_1;
    String address_2;
    String city;
    String addressPostcode;
    String email;
    int countOfAvailablePlaces;
    int totalPlaces;
    String facilities;
    String targetAudience;
}

