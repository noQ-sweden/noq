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
    String address_1;
    String address_2;
    String city;
    String addressPostcode;
    String email;
    int countOfAvailablePlaces;
    int totalPlaces;
    String facilities;
    String targetAudience;

    public static Host create(String name,
                              String address_1,
                              String address_2,
                              String city,
                              String addressPostcode,
                              String email,
                              int countOfAvailablePlaces,
                              int totalPlaces,
                              String facilities,
                              String targetAudience) {
        return Host.builder()
                .name(name)
                .address_1(address_1)
                .address_2(address_2)
                .city(city)
                .addressPostcode(addressPostcode)
                .email(email)
                .countOfAvailablePlaces(countOfAvailablePlaces)
                .totalPlaces(totalPlaces)
                .facilities(facilities)
                .targetAudience(targetAudience)
                .build();
    }
}

