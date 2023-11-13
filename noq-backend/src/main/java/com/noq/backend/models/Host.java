package com.noq.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Entity(name = "HOST")
public class Host {

    @Id
    @GeneratedValue
    private String id;
    private String name;
}
