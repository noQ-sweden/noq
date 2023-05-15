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

    private Status status;
    private LocalDateTime reservedTime; // Date

    public Reservation(Bed  bed, User user, Status status, LocalDateTime reservedTime) {
        this.id = UUID.randomUUID().toString();
        this.bed = bed;
        this.user = user;
        this.status = status;
        this.reservedTime = reservedTime;
    }
}
