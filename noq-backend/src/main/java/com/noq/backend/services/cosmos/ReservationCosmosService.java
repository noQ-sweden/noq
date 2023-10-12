package com.noq.backend.services.cosmos;

import com.noq.backend.DTO.cosmos.CreateReservationDTO;
import com.noq.backend.exeptions.HostNotFoundException;
import com.noq.backend.models.cosmos.HostCosmos;
import com.noq.backend.models.cosmos.ReservationCosmos;
import com.noq.backend.models.cosmos.UserCosmos;
import com.noq.backend.repository.cosmos.HostRepositoryCosmos;
import com.noq.backend.repository.cosmos.ReservationRepositoryCosmos;
import com.noq.backend.repository.cosmos.UserRepositoryCosmos;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static com.noq.backend.models.Status.PENDING;
import static com.noq.backend.models.Status.RESERVED;
import static com.noq.backend.services.cosmos.utils.ErrorHandler.handleReservationNotFound;
import static com.noq.backend.services.cosmos.utils.InputValidator.IdField.*;
import static com.noq.backend.services.cosmos.utils.InputValidator.validateInputId;

@Service
@AllArgsConstructor
public class ReservationCosmosService implements ReservationCosmosServiceI {
    private final HostRepositoryCosmos hosts;
    private final UserRepositoryCosmos users;
    private final ReservationRepositoryCosmos reservations;
    private final BedCosmosService bedService;

    public Mono<ReservationCosmos> createReservation(CreateReservationDTO request) {
        validateInputData(request);

        return hosts
                .findById(request.hostId())
                .flatMap(host -> users
                        .findById(request.userId())
                        .flatMap(user -> createAndSaveReservation(request.bedId(), host, user)));
    }

    private static void validateInputData(CreateReservationDTO request) {
        validateInputId(HOST_ID, request.hostId());
        validateInputId(BED_ID, request.bedId());
        validateInputId(USER_ID, request.userId());
    }

    private Mono<ReservationCosmos> createAndSaveReservation(String bedId, HostCosmos host, UserCosmos user) {
        ReservationCosmos reservation = new ReservationCosmos(host, user, PENDING);
        return reservations
                .save(reservation)
                .then(bedService.updateBedStatus(bedId, host.getHostId(), true))
                .thenReturn(reservation);
    }

    public Mono<ReservationCosmos> getReservationByUserId(String userId) {
        validateInputId(USER_ID, userId);
        return users
                .findById(userId)
                .flatMap(reservations::findReservationCosmosByUser)
                .switchIfEmpty(handleReservationNotFound(userId));
    }

    public Flux<ReservationCosmos> getReservationsByHostId(String hostId) {
        validateInputId(HOST_ID, hostId);
        return hosts
                .findById(hostId)
                .switchIfEmpty(Mono.error(new HostNotFoundException(hostId)))
                .flatMapMany(reservations::findReservationCosmosByHost)
                .switchIfEmpty(Flux.empty());
    }

    public Flux<ReservationCosmos> getReservationsByHostIdStatusPending(String hostId) {
        validateInputId(HOST_ID, hostId);
        return hosts
                .findById(hostId)
                .flatMapMany(host -> reservations.findReservationCosmosByHostAndStatus(host, PENDING))
                .switchIfEmpty(Flux.empty());
    }


    public Flux<ReservationCosmos> getReservationsByHostIdStatusReserved(String hostId) {
        validateInputId(HOST_ID, hostId);
        return hosts
                .findById(hostId)
                .flatMapMany(host -> reservations.findReservationCosmosByHostAndStatus(host, RESERVED))
                .switchIfEmpty(Flux.empty());
    }

    public Flux<ReservationCosmos> approveReservations(List<String> reservationsId) {
        return Flux.fromIterable(reservationsId)
                .flatMap(id -> {
                    validateInputId(RESERVATION_ID, id);
                    return updateAndSaveReservationStatus(id);
                })
                .collectList()
                .flatMapMany(Flux::fromIterable);
    }

    private Mono<ReservationCosmos> updateAndSaveReservationStatus(String id) {
        return reservations
                .findById(id)
                .map(reservation -> {
                    reservation.setStatus(RESERVED);
                    return reservation;
                })
                .flatMap(reservations::save);
    }

    /* PARAM FUNCTIONS */
    public <P> Function<P, Mono<P>> updateParamWithReservations(Function<P, HostCosmos> getHost, BiConsumer<P, List<ReservationCosmos>> setReservations) {
        return param -> getReservationsByHostId(getHost.apply(param).getHostId())
                .collectList()
                .doOnNext(reservationCosmos -> setReservations.accept(param, reservationCosmos))
                .thenReturn(param);
    }
    /* PARAM FUNCTIONS */
}
