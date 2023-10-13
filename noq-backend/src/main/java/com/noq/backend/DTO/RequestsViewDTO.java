package com.noq.backend.DTO;

import com.noq.backend.models.Reservation;

public record RequestsViewDTO(
        String id,
        Request[] reservations
) {

    public record Request(
            String id,
            String name,
            int queuingPlace,
            Reservation.Status status
    ) {
    }

}
