package com.noq.backend.services;

import com.noq.backend.controllers.might_delete.DTOs.CreateReservationDTO;
import com.noq.backend.exceptions.HostNotFoundException;
import com.noq.backend.exceptions.ReservationNotFoundException;
import com.noq.backend.models.Host;
import com.noq.backend.models.Reservation;
import com.noq.backend.repositories.HostRepository;
import com.noq.backend.repositories.ReservationRepository;
import com.noq.backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.noq.backend.utilities.InputValidator.IdField.*;
import static com.noq.backend.utilities.InputValidator.validateInputId;

@Service
@AllArgsConstructor
public class ReservationService implements ReservationServiceI {
    private final HostRepository hostRepository;
    private final UserRepository usersUserRepository;
    private final ReservationRepository reservationRepository;

//    public Mono<Reservation> createReservation(CreateReservationDTO request) {
//        validateInputData(request);
//
//        return hostRepository
//                .findById(request.hostId())
//                .flatMap(host -> usersUserRepository
//                        .findById(request.userId())
//                        .flatMap(user -> createAndSaveReservation(request.bedId(), host, user)));
//    }

    private static void validateInputData(CreateReservationDTO request) {
        validateInputId(HOST_ID, request.hostId());
        validateInputId(BED_ID, request.bedId());
        validateInputId(USER_ID, request.userId());
    }

//    private Mono<Reservation> createAndSaveReservation(String bedId, Host host, User user) {
//        Reservation reservation = Reservation.create(host, user);
//        return reservationRepository
//                .save(reservation)
////                .then(bedService.updateBedStatus(bedId, host.getHostId(), true))
//                .thenReturn(reservation);
//    }

//    public Reservation getReservationByUserId(String userId) {
//        validateInputId(USER_ID, userId);
//        return usersUserRepository
//                .findById(userId)
//                .flatMap(reservationRepository::findReservationCosmosByUser);
//    }

    public Iterable<Reservation> findReservationsByHostId(String hostId) {
        Host host = hostRepository.findByHostId(hostId).orElseThrow(() -> new HostNotFoundException(hostId));
        return findReservationsByHost(host);
    }

    public Iterable<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    private Iterable<Reservation> findReservationsByHost(Host host) {
        return StreamSupport.stream(reservationRepository.findAll().spliterator(), false)
                .filter(reservation -> reservation.getHost().getHostId().equals(host.getHostId()))
                .collect(Collectors.toList());
    }

//    public Flux<Reservation> getReservationsByHostIdStatusPending(String hostId) {
//        validateInputId(HOST_ID, hostId);
//        return hostRepository
//                .findById(hostId)
//                .flatMapMany(host -> reservationRepository.findReservationCosmosByHostAndStatus(host, Reservation.Status.PENDING))
//                .switchIfEmpty(Flux.empty());
//    }


//    public Flux<Reservation> getReservationsByHostIdStatusReserved(String hostId) {
//        validateInputId(HOST_ID, hostId);
//        return hostRepository
//                .findById(hostId)
//                .flatMapMany(host -> reservationRepository.findReservationCosmosByHostAndStatus(host, Reservation.Status.RESERVED))
//                .switchIfEmpty(Flux.empty());
//    }

//    public Flux<Reservation> approveReservations(List<String> reservationsId) {
//        return Flux.fromIterable(reservationsId)
//                .flatMap(id -> {
//                    validateInputId(RESERVATION_ID, id);
//                    return updateAndSaveReservationStatus(id);
//                })
//                .collectList()
//                .flatMapMany(Flux::fromIterable);
//    }

//    private Mono<Reservation> updateAndSaveReservationStatus(String id) {
//        return reservationRepository
//                .findById(id)
//                .map(reservation -> {
//                    reservation.setStatus(RESERVED);
//                    return reservation;
//                })
//                .flatMap(reservationRepository::save);
//    }

    @Override
    public Reservation updateReservationField(String reservationId, String newValue, Reservation.UpdateChangeType updateChangeType) {
        return reservationRepository.findById(reservationId)
                .map(reservation -> {
                    switch (updateChangeType) {
                        case UPDATE_STATUS -> {
                            reservation.setStatus(Reservation.Status.valueOf(newValue));
                            return reservationRepository.save(reservation);
                        }
                        default -> {
                            throw new IllegalArgumentException("Invalid ChangeFieldName: " + updateChangeType);
                        }
                    }
                })
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));
    }

    /*DTO_BUILDER_FUNCTIONS*/
    public <B> Function<B, B> updateDTOBuilderWithReservations(Function<B, Host> getHost, BiConsumer<B, List<Reservation>> setReservations) {
        return dtoBuilder -> {
            List<Reservation> reservations = StreamSupport.stream(findReservationsByHostId(getHost.apply(dtoBuilder).getHostId()).spliterator(), false)
                    .toList();
            setReservations.accept(dtoBuilder, reservations);
            return dtoBuilder;
        };
    }

    public <B> Function<B, B> updateDTOBuilderWithReservations(BiConsumer<B, List<Reservation>> setReservations) {
        return dtoBuilder -> {
            List<Reservation> reservations = StreamSupport.stream(findAllReservations().spliterator(), false)
                    .toList();
            setReservations.accept(dtoBuilder, reservations);
            return dtoBuilder;
        };
    }
    /*DTO_BUILDER_FUNCTIONS*/

}
