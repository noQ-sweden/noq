package com.noq.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateReservation {
    private String hostId;
    private String userId;
    private String bedId;
}
