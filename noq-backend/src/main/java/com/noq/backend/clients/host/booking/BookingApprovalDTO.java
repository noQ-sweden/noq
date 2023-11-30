package com.noq.backend.clients.host.booking;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class BookingApprovalDTO {
    String bookingId;
    String approvalStatus;
}
