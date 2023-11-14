package com.noq.backend.models;

import com.noq.backend.models.utils.CommaSeparatedListConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Value
@Builder
@Entity(name = "USER")
public class User {

    @Id
    @NonNull
    UUID userId;
    @NonNull
    String firstName;
    @NonNull
    String lastName;
    String gender;
    @NonNull
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
