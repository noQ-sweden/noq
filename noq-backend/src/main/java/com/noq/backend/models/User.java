package com.noq.backend.models;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
//import jakarta.persistence.Id;
import java.util.UUID;

@Data
@Entity(name="user")
@NoArgsConstructor
public class User {
    @Id @Column(name = "id", nullable = false)
    private UUID id;

    private String username;
    private String password;

    private boolean reservation;

    public User(String username, String password) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.reservation = false;
    }
}
