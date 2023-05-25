package com.noq.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Reservation {
    @Id @Column(name = "id", nullable = false)
    private String reservationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Host host;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime reservedTime;
    private Status status;

    public Reservation(Host host, User user, Status status) {
        this.reservationId = UUID.randomUUID().toString();
        this.host = host;
        this.user = user;
        this.status = status;
    }
}
