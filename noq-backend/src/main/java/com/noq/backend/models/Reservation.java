package com.noq.backend.models;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import java.util.UUID;

@Data
@Entity(name="reservation")
@NoArgsConstructor
public class Reservation {
    @Id @Column(name = "id", nullable = false)
    private String id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bed_id")
    private Bed bed;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime reservedTime; // Date

}
