package com.noq.backend.utils;

import com.noq.backend.DTO.BedDTO;
import com.noq.backend.exeptions.BedNotFoundException;
import com.noq.backend.exeptions.HostNotFoundException;
import com.noq.backend.exeptions.ReservationNotFoundException;
import com.noq.backend.models.Bed;
import com.noq.backend.models.Host;
import com.noq.backend.models.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

public class ErrorHandler {
    public static Mono<BedDTO> handleError(Throwable error) {
        return Mono.error(new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Oops! Something went wrong, and I don't know why!",
                error
        ));
    }

    public static Mono<Bed> handleBedNotFound(String bedId) {
        return Mono.error(() -> new BedNotFoundException("There is no bed with id " + bedId));
    }

    public static Mono<Host> handleHostNotFound(String hostId) {
        return Mono.error(() -> new HostNotFoundException("There is no host with id " + hostId));
    }

    public static Mono<Reservation> handleReservationNotFound(String userId) {
        return Mono.error(() -> new ReservationNotFoundException("There is no reservation for user with id " + userId));
    }
}
