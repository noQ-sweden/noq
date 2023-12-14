package com.noq.backend.clients.host.bookings.reqBody;

public record BookingApprovalReqBody(
        String hostId,
        String bookingId
) {
}
