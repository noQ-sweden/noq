package com.noq.backend.DTO;

import com.noq.backend.models.Host;
import com.noq.backend.models.Status;
import com.noq.backend.models.User;

public record ReservationDTO(String reservationId, Host host, User user, Status status ) {

}
