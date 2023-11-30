package com.noq.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Value
@Builder(toBuilder = true)
@Entity(name = "BOOKING")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID bookingId;

    LocalDateTime startDateTime;
    LocalDateTime endDateTime;

    // TODO Model for this when Handläggare (Case Manager) is implemented
    String caseManagerName;
    // TODO Model for this Handläggare (Case Manager) is implemented
    String caseManagerEmail;

    UUID hostId;
    UUID userId;

    BookingStatus bookingStatus;
    ApprovalStatus approvalStatus;
}
