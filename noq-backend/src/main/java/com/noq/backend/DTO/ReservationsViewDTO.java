package com.noq.backend.DTO;

import com.noq.backend.models.Address;

public record ReservationsViewDTO (String reservationId, String hostName, String hostImage, Address address){
}
