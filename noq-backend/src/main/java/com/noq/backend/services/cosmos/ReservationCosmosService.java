package com.noq.backend.services.cosmos;

import com.noq.backend.DTO.cosmos.CreateReservationRequest;
import com.noq.backend.models.cosmos.ReservationCosmos;
import com.noq.backend.repository.cosmos.BedRepositoryCosmos;
import com.noq.backend.repository.cosmos.HostRepositoryCosmos;
import com.noq.backend.repository.cosmos.ReservationRepositoryCosmos;
import com.noq.backend.repository.cosmos.UserRepositoryCosmos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.noq.backend.models.Status.PENDING;
import static com.noq.backend.services.cosmos.utils.ErrorHandler.handleReservationNotFound;
import static com.noq.backend.services.cosmos.utils.InputValidator.IdField.*;
import static com.noq.backend.services.cosmos.utils.InputValidator.validateId;

@Service
public class ReservationCosmosService {
    private final HostRepositoryCosmos hosts;
    private final UserRepositoryCosmos users;
    private final ReservationRepositoryCosmos reservations;
    private final BedRepositoryCosmos beds;
    private final BedCosmosService bedService;

    @Autowired
    public ReservationCosmosService(
            HostRepositoryCosmos hosts,
            UserRepositoryCosmos users,
            ReservationRepositoryCosmos reservations,
            BedRepositoryCosmos beds,
            BedCosmosService bedService
    ) {
        this.hosts = hosts;
        this.users = users;
        this.reservations = reservations;
        this.beds = beds;
        this.bedService = bedService;
    }

    public Mono<ReservationCosmos> createReservation(CreateReservationRequest request) {
        validateId(HOST_ID, request.hostId());
        validateId(BED_ID, request.bedId());
        validateId(USER_ID, request.userId());

        return hosts
                .findById(request.hostId())
                .flatMap(host -> users
                        .findById(request.userId())
                        .flatMap(user -> {
                            // TODO: Update list of beds in Host-object?
                            ReservationCosmos reservation = new ReservationCosmos(host, user, PENDING);
                            return reservations
                                    .save(reservation)
                                    .then(bedService.updateBedStatus(request.bedId(), request.hostId(), true))
                                    .thenReturn(reservation);
                        }));
    }

    public Mono<ReservationCosmos> getReservationByUserId(String userId) {
        validateId(USER_ID, userId);
        return users
                .findById(userId)
                .flatMap(reservations::findReservationCosmosByUser)
                .switchIfEmpty(handleReservationNotFound(userId));
    }

    public Flux<ReservationCosmos> getReservationsByHostId(String hostId) {
        validateId(HOST_ID, hostId);
        return hosts
                .findById(hostId)
                .flatMapMany(reservations::findReservationCosmosByHost)
                .switchIfEmpty(Flux.empty());
    }

    public List<ReservationCosmos> getReservationsByHostIdStatusPending(String hostId) {
        return null;
//        return reservationRepository.getAllReservations().stream()
//                .filter(reservation ->
//                        reservation.getHost().getHostId().equals(hostId) &&
//                                reservation.getStatus() == Status.PENDING)
//                .collect(Collectors.toList());
    }


    public List<ReservationCosmos> approveReservations(List<String> reservationsId) {
        return null;
//        List<Reservation> reservations = reservationRepository.getAllReservations().stream()
//                .filter(res -> {
//                    if (reservationsId.contains(res.getReservationId())) {
//                        res.setStatus(Status.RESERVED);
//                        return true;
//                    }
//                    return false;
//                })
//                .collect(Collectors.toList());
//        reservationRepository.saveAll(reservations);
//        return reservations;
    }

    public List<ReservationCosmos> getReservationsByHostIdStatusReserved(String hostId) {
        return null;
//        return reservationRepository.getAllReservations().stream()
//                .filter(reservation ->
//                        reservation.getHost().getHostId().equals(hostId) &&
//                                reservation.getStatus() == Status.RESERVED)
//                .collect(Collectors.toList());
    }
}
