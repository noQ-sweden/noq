package com.noq.backend.utilities;

//import com.noq.backend.controllers.host.BedsViewController.BedDTO;
import com.noq.backend.exceptions.BedNotFoundException;
import com.noq.backend.exceptions.HostNotFoundException;
import com.noq.backend.exceptions.ReservationNotFoundException;
import com.noq.backend.models.Bed;
import com.noq.backend.models.Host;
import com.noq.backend.models.Reservation;


public class ErrorHandler {
//    public static Mono<BedDTO> handleError(Throwable error) {
//        return Mono.error(new ResponseStatusException(
//                HttpStatus.INTERNAL_SERVER_ERROR,
//                "Oops! Something went wrong, and I don't know why!",
//                error
//        ));
//    }
//
//    public static Mono<Bed> handleBedNotFound(String bedId) {
//        return Mono.error(() -> new BedNotFoundException("There is no bed with id " + bedId));
//    }
//
//    public static Mono<Host> handleHostNotFound(String hostId) {
//        return Mono.error(() -> new HostNotFoundException("There is no host with id " + hostId));
//    }
//
//    public static Mono<Reservation> handleReservationNotFound(String userId) {
//        return Mono.error(() -> new ReservationNotFoundException("There is no reservation for user with id " + userId));
//    }
}
