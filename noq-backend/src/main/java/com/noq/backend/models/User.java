package com.noq.backend.models;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Data
@Entity(name="users")
public class User {
    @Id @Column(name = "id", nullable = false)
    private Long id;
    private String name;
}
