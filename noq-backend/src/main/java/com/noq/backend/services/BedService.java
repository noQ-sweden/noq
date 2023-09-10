package com.noq.backend.services;

import com.azure.cosmos.models.PartitionKey;
import com.noq.backend.DTO.BedDTO;
import com.noq.backend.exeptions.BedNotFoundException;
import com.noq.backend.exeptions.HostNotFoundException;
import com.noq.backend.models.Bed;
import com.noq.backend.models.HostCosmos;
import com.noq.backend.repository.BedRepositoryCosmos;
import com.noq.backend.repository.HostRepositoryCosmos;
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
    private final HostRepositoryCosmos hosts;

    public Mono<BedDTO> createBed(String hostId) {
        return hosts
                .findById(hostId)
                .switchIfEmpty(handleHostNotFound(hostId))
                .flatMap(host -> {
                    Bed bed = new Bed(host);
                    return beds
                            .save(bed)
                            .map(this::toDTO)
                            .onErrorResume(this::handleError);
                });
    }

    public Mono<BedDTO> findBedById(String bedId, String hostId) {
        return hosts
                .findById(hostId)
                .switchIfEmpty(handleHostNotFound(hostId))
                .flatMap(host -> beds
                        .findById(bedId, new PartitionKey(hostId))
                        .switchIfEmpty(handleBedNotFound(bedId))
                        .map(this::toDTO)
                        .onErrorResume(this::handleError));
    }

    public Flux<BedDTO> findAllBedsByHostId(String hostId) {
        return hosts
                .findById(hostId)
                .switchIfEmpty(handleHostNotFound(hostId))
                .flatMapMany(host -> beds
                        .findBedsByHost(host)
                        .map(this::toDTO));
    }

    public Flux<BedDTO> findAllBeds() {
        return beds
                .findAll()
                .map(this::toDTO)
                .onErrorResume(this::handleError);
    }

    public Mono<BedDTO> updateBedStatus(String bedId, String hostId, boolean reserved) {
        return hosts
                .findById(hostId)
                .switchIfEmpty(handleHostNotFound(hostId))
                .flatMap(host -> beds
                        .findById(bedId, new PartitionKey(host))
                        .switchIfEmpty(handleBedNotFound(bedId))
                        .flatMap(bed -> {
                            boolean updateNeeded = false;
                            if (!reserved == bed.getReserved()) {
                                updateNeeded = true;
                                bed.setReserved(reserved);
                            }
                            if (updateNeeded)
                                return beds.save(bed).map(this::toDTO);
                            return Mono.just(toDTO(bed));
                        })
                        .onErrorResume(this::handleError));
    }

    public Mono<BedDTO> deleteBedById(String bedId, String hostId) {
        return hosts
                .findById(hostId)
                .switchIfEmpty(handleHostNotFound(hostId))
                .flatMap(host -> beds
                        .findById(bedId, new PartitionKey(host))
                        .switchIfEmpty(handleBedNotFound(bedId))
                        .flatMap(bed -> beds.deleteById(bedId, new PartitionKey(host))
                                .then(Mono.just(toDTO(bed))))
                        .onErrorResume(this::handleError));
    }

    private BedDTO toDTO(Bed bed) {
        return new BedDTO(bed.getBedId(), bed.getHost(), bed.getReserved());
    }

    private static Mono<Bed> handleBedNotFound(String id) {
        return Mono.error(() -> new BedNotFoundException("There is no bed with id " + id));
    }

    private static Mono<HostCosmos> handleHostNotFound(String id) {
        return Mono.error(() -> new HostNotFoundException("There is no host with id " + id));
    }

    private Mono<BedDTO> handleError(Throwable error) {
        return Mono.error(new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Oops! Something went wrong, and I don't know why!",
                error
        ));
    }
}

