package com.noq.backend.clients.host.bookings.reqBody;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class BookingApprovalDTO {
    String bookingId;
    String approvalStatus;
}
