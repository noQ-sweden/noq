package com.noq.backend.controllers.host.RequestsViewController;

public record HostRequestsViewDTO(
        String id,
        Reservation[] reservations
) {

    public record Reservation(
            String id,
            String name,
            int queuingPlace,
            com.noq.backend.models.Reservation.Status status
    ) {
    }

}
