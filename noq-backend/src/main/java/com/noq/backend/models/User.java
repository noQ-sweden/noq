package com.noq.backend.models;

import com.noq.backend.models.utils.CommaSeparatedListConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Value
@Builder(toBuilder = true)
@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID userId;
    @NonNull
    String firstName;
    @NonNull
    String lastName;
    @NonNull
    String dateOfBirth;
    String gender;
    String unokod;
    String phone;
    String email;
    String kommun;
    String place;
    boolean payingCharges;
    @NonNull
    String caseManager;
    boolean understandsSwedish;
    @Convert(converter = CommaSeparatedListConverter.class)
    List<String> languagesKnown;
    boolean isFlagged;
    String reasonsForHomelessness;
    String goals;
    String commentsAndRemarks;
    UUID reservationId;
}
