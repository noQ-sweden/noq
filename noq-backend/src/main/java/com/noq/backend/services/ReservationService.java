package com.noq.backend.services;

import com.noq.backend.controllers.might_delete.DTOs.CreateReservationDTO;
import com.noq.backend.exceptions.HostNotFoundException;
import com.noq.backend.models.Host;
import com.noq.backend.models.Reservation;
import com.noq.backend.models.User;
import com.noq.backend.repositories.HostRepository;
import com.noq.backend.repositories.ReservationRepository;
import com.noq.backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static com.noq.backend.models.Reservation.Status.RESERVED;
import static com.noq.backend.utilities.ErrorHandler.handleReservationNotFound;
import static com.noq.backend.utilities.InputValidator.IdField.*;
import static com.noq.backend.utilities.InputValidator.validateInputId;

@Service
@AllArgsConstructor
public class ReservationService implements ReservationServiceI {
    private final HostRepository hostRepository;
    private final UserRepository usersUserRepository;
    private final ReservationRepository reservationRepository;
//    private final BedService bedService;

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
        Reservation reservation = Reservation.create(host, user);
        return reservationRepository
                .save(reservation)
//                .then(bedService.updateBedStatus(bedId, host.getHostId(), true))
                .thenReturn(reservation);
    }

    public Mono<Reservation> getReservationByUserId(String userId) {
        validateInputId(USER_ID, userId);
        return usersUserRepository
                .findById(userId)
                .flatMap(reservationRepository::findReservationCosmosByUser)
                .switchIfEmpty(handleReservationNotFound(userId));
    }

    public Flux<Reservation> findReservationsByHostId(String hostId) {
        validateInputId(HOST_ID, hostId);
        return hostRepository
                .findByHostId(hostId)
                .switchIfEmpty(Mono.error(new HostNotFoundException(hostId)))
                .flatMapMany(this::findReservationsByHost);
    }

    public Flux<Reservation> findAllReservations() {
        return reservationRepository.findAll();
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

 /*DTO_BUILDER_FUNCTIONS*/

    public <B> Function<B, Mono<B>> updateDTOBuilderWithReservations(Function<B, Host> getHost, BiConsumer<B, List<Reservation>> setReservations) {
        return dtoBuilder -> findReservationsByHostId(getHost.apply(dtoBuilder).getHostId())
                .collectList()
                .doOnNext(reservations -> setReservations.accept(dtoBuilder, reservations))
                .thenReturn(dtoBuilder);
    }
    public <B> Function<B, Mono<B>> updateDTOBuilderWithReservations(BiConsumer<B, List<Reservation>> setReservations) {
        return dtoBuilder -> findAllReservations()
                .collectList()
                .doOnNext(reservations -> setReservations.accept(dtoBuilder, reservations))
                .thenReturn(dtoBuilder);
    }
    /*DTO_BUILDER_FUNCTIONS*/

}
