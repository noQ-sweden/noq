package com.noq.backend.services;

import com.noq.backend.exceptions.HostNotFoundException;
import com.noq.backend.models.Host;
import com.noq.backend.models.Reservation;
import com.noq.backend.repositories.HostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ReservationService implements ReservationServiceI {
    private final HostRepository hostRepository;

    public Iterable<Reservation> findReservationsByHostId(String hostId) {
        Host host = hostRepository.findById(hostId).orElseThrow(() -> new HostNotFoundException(hostId));
        return findReservationsByHost(host);
    }

    public Iterable<Reservation> findAllReservations() {
        throw new RuntimeException("findAllReservations Not Implemented");
    }

    private Iterable<Reservation> findReservationsByHost(Host host) {
        throw new RuntimeException("findReservationsByHost Not Implemented");
    }

    @Override
    public Reservation updateReservationField(String reservationId, String newValue, Reservation.UpdateChangeType updateChangeType) {
        throw new RuntimeException("updateReservationField Not Implemented");
    }

    /*DTO_BUILDER_FUNCTIONS*/
    public <B> Function<B, B> updateDTOBuilderWithReservations(Function<B, Host> getHost, BiConsumer<B, List<Reservation>> setReservations) {
        return dtoBuilder -> {
            List<Reservation> reservations = StreamSupport.stream(findReservationsByHostId(getHost.apply(dtoBuilder).getId()).spliterator(), false)
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
