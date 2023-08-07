package com.noq.backend.DTO;

import com.noq.backend.models.Host;
import com.noq.backend.models.Status;
import com.noq.backend.models.User;

public record ReservationDTO(String reservationId, HostDTO hostDTO, UserDTO userDTO, Status status ) {

}
