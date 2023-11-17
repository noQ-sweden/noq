package com.noq.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

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
    String address1;
    String address2;
    String city;
    String addressPostcode;
    String email;
    int countOfAvailablePlaces;
    int totalPlaces;
    String facilities;
    String targetAudience;
}

