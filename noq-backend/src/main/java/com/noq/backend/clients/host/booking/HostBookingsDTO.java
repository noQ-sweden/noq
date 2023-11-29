package com.noq.backend.clients.host.booking;

import com.noq.backend.clients.user.availableHosts.dto.HostDTO;
import com.noq.backend.models.Booking;

import java.util.List;

record HostBookingsDTO(
        List<Booking> bookings
) {
}
