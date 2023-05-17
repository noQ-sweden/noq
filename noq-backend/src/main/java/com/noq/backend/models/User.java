package com.noq.backend.models;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.util.UUID;

@Data
@Entity(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id @Column(name = "id", nullable = false)
    private String id;
    private String name;
    private Boolean reservation;
}
