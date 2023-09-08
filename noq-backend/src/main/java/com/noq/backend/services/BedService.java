package com.noq.backend.services;

import com.azure.cosmos.models.PartitionKey;
import com.noq.backend.DTO.BedDTO;
import com.noq.backend.DTO.BedRequestDTO;
import com.noq.backend.exeptions.BedNotFoundException;
import com.noq.backend.models.Bed;
import com.noq.backend.models.Host;
import com.noq.backend.repository.BedRepositoryCosmos;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BedService {
    private final BedRepositoryCosmos beds;

    public Mono<BedDTO> createBed(BedRequestDTO request) {
        Bed bed = new Bed(request.host());
        return beds
                .save(bed)
                .map(this::toDTO)
                .onErrorResume(this::handleError);
    }

    public Mono<BedDTO> findBedById(String id, Host host) {
        return beds
                .findById(id, new PartitionKey(host))
                .switchIfEmpty(handleBedNotFound(id))
                .map(this::toDTO)
                .onErrorResume(this::handleError);
    }

    public Flux<BedDTO> findAllBeds() {
        return beds
                .findAll()
                .map(this::toDTO)
                .onErrorResume(this::handleError);
    }

    public Mono<BedDTO> updateBedStatus(BedDTO request) {
        return beds
                .findById(request.id(), new PartitionKey(request.host()))
                .switchIfEmpty(handleBedNotFound(request.id()))
                .flatMap(bed -> {
                    boolean updateNeeded = false;
                    if (!request.reserved() == bed.getReserved()) {
                        updateNeeded = true;
                        bed.setReserved(request.reserved());
                    }
                    if (updateNeeded)
                        return beds.save(bed).map(this::toDTO);
                    return Mono.just(toDTO(bed));
                })
                .onErrorResume(this::handleError);
    }

    public Mono<BedDTO> deleteBedById(String id, Host host) {
        return beds
                .findById(id, new PartitionKey(host))
                .switchIfEmpty(handleBedNotFound(id))
                .flatMap(bed -> beds.deleteById(id, new PartitionKey(host))
                                .then(Mono.just(toDTO(bed))))
                .onErrorResume(this::handleError);
    }

    private BedDTO toDTO(Bed bed) {
        return new BedDTO(bed.getBedId(), bed.getHost(), bed.getReserved());
    }

    private static Mono<Bed> handleBedNotFound(String id) {
        return Mono.error(() -> new BedNotFoundException("There is no bed with id " + id));
    }

    private Mono<BedDTO> handleError(Throwable error) {
        return Mono.error(new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Oops! Something went wrong, and I don't know why!",
                error
        ));
    }
}

