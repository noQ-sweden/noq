package com.noq.backend.clients.host.requests;

public record RequestsPageDTO(
        String id,
        Reservation[] approvedBookings,
        Reservation[] disapprovedBookings,
        Reservation[] pendingBookings
) {
    public record Reservation(
            String id,
            String userId,
            String name,
            String unoCode,
            int queuingPlace,
            String status
    ) {
    }
}
