package com.noq.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateReservation {
    private Host host;
    private String userId;
}
