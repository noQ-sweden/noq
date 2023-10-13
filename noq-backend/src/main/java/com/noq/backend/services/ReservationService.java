package com.noq.backend.services;

import com.noq.backend.DTO.CreateReservationDTO;
import com.noq.backend.exeptions.HostNotFoundException;
import com.noq.backend.models.Host;
import com.noq.backend.models.Reservation;
import com.noq.backend.models.User;
import com.noq.backend.repository.HostRepository;
import com.noq.backend.repository.ReservationRepository;
import com.noq.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static com.noq.backend.models.Reservation.Status.PENDING;
import static com.noq.backend.models.Reservation.Status.RESERVED;
import static com.noq.backend.utils.ErrorHandler.handleReservationNotFound;
import static com.noq.backend.utils.InputValidator.IdField.*;
import static com.noq.backend.utils.InputValidator.validateInputId;

@Service
@AllArgsConstructor
public class ReservationService implements ReservationServiceI {
    private final HostRepository hostRepository;
    private final UserRepository usersUserRepository;
    private final ReservationRepository reservationRepository;
    private final BedService bedService;

    public Mono<Reservation> createReservation(CreateReservationDTO request) {
        validateInputData(request);

        return hostRepository
                .findById(request.hostId())
                .flatMap(host -> usersUserRepository
                        .findById(request.userId())
                        .flatMap(user -> createAndSaveReservation(request.bedId(), host, user)));
    }

    private static void validateInputData(CreateReservationDTO request) {
        validateInputId(HOST_ID, request.hostId());
        validateInputId(BED_ID, request.bedId());
        validateInputId(USER_ID, request.userId());
    }

    private Mono<Reservation> createAndSaveReservation(String bedId, Host host, User user) {
        Reservation reservation = new Reservation(host, user, PENDING);
        return reservationRepository
                .save(reservation)
                .then(bedService.updateBedStatus(bedId, host.getHostId(), true))
                .thenReturn(reservation);
    }

    public Mono<Reservation> getReservationByUserId(String userId) {
        validateInputId(USER_ID, userId);
        return usersUserRepository
                .findById(userId)
                .flatMap(reservationRepository::findReservationCosmosByUser)
                .switchIfEmpty(handleReservationNotFound(userId));
    }

    public Flux<Reservation> getReservationsByHostId(String hostId) {
        validateInputId(HOST_ID, hostId);
        return hostRepository
                .findByHostId(hostId)
                .switchIfEmpty(Mono.error(new HostNotFoundException(hostId)))
                .flatMapMany(this::findReservationsByHost);
    }

    private Flux<Reservation> findReservationsByHost(Host host) {
        return reservationRepository.findAll()
                .filter(reservationCosmos -> reservationCosmos.getHost().getHostId().equals(host.getHostId()));
    }

    public Flux<Reservation> getReservationsByHostIdStatusPending(String hostId) {
        validateInputId(HOST_ID, hostId);
        return hostRepository
                .findById(hostId)
                .flatMapMany(host -> reservationRepository.findReservationCosmosByHostAndStatus(host, Reservation.Status.PENDING))
                .switchIfEmpty(Flux.empty());
    }


    public Flux<Reservation> getReservationsByHostIdStatusReserved(String hostId) {
        validateInputId(HOST_ID, hostId);
        return hostRepository
                .findById(hostId)
                .flatMapMany(host -> reservationRepository.findReservationCosmosByHostAndStatus(host, Reservation.Status.RESERVED))
                .switchIfEmpty(Flux.empty());
    }

    public Flux<Reservation> approveReservations(List<String> reservationsId) {
        return Flux.fromIterable(reservationsId)
                .flatMap(id -> {
                    validateInputId(RESERVATION_ID, id);
                    return updateAndSaveReservationStatus(id);
                })
                .collectList()
                .flatMapMany(Flux::fromIterable);
    }

    private Mono<Reservation> updateAndSaveReservationStatus(String id) {
        return reservationRepository
                .findById(id)
                .map(reservation -> {
                    reservation.setStatus(RESERVED);
                    return reservation;
                })
                .flatMap(reservationRepository::save);
    }

    /* PARAM FUNCTIONS */
    public <P> Function<P, Mono<P>> updateParamWithReservations(Function<P, Host> getHost, BiConsumer<P, List<Reservation>> setReservations) {
        return param -> getReservationsByHostId(getHost.apply(param).getHostId())
                .collectList()
                .doOnNext(reservationCosmos -> setReservations.accept(param, reservationCosmos))
                .thenReturn(param);
    }
    /* PARAM FUNCTIONS */
}
