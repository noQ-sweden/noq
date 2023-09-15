package com.noq.backend.services.cosmos;

import com.azure.cosmos.models.PartitionKey;
import com.noq.backend.DTO.cosmos.BedDTO;
import com.noq.backend.models.cosmos.BedCosmos;
import com.noq.backend.repository.cosmos.BedRepositoryCosmos;
import com.noq.backend.repository.cosmos.HostRepositoryCosmos;
import com.noq.backend.services.cosmos.utils.ErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.noq.backend.services.cosmos.utils.ErrorHandler.handleBedNotFound;
import static com.noq.backend.services.cosmos.utils.ErrorHandler.handleHostNotFound;
import static com.noq.backend.services.cosmos.utils.InputValidator.*;
import static com.noq.backend.services.cosmos.utils.InputValidator.IdField.*;

@Service
public class BedCosmosService {
    private final BedRepositoryCosmos beds;
    private final HostRepositoryCosmos hosts;

    @Autowired
    public BedCosmosService(BedRepositoryCosmos beds, HostRepositoryCosmos hosts) {
        this.beds = beds;
        this.hosts = hosts;
    }

    public Mono<BedDTO> createBed(String hostId) {
        validateInputId(HOST_ID, hostId);
        return hosts
                .findById(hostId)
                .switchIfEmpty(handleHostNotFound(hostId))
                .flatMap(host -> {
                    BedCosmos bed = new BedCosmos(host);
                    return beds
                            .save(bed)
                            .map(this::toDTO)
                            .onErrorResume(ErrorHandler::handleError);
                });
    }

    public Mono<BedDTO> findBedById(String bedId, String hostId) {
        validateInputId(HOST_ID, hostId);
        validateInputId(BED_ID, bedId);
        return hosts
                .findById(hostId)
                .switchIfEmpty(handleHostNotFound(hostId))
                .flatMap(host -> beds
                        .findById(bedId, new PartitionKey(hostId))
                        .switchIfEmpty(handleBedNotFound(bedId))
                        .map(this::toDTO)
                        .onErrorResume(ErrorHandler::handleError));
    }

    public Flux<BedDTO> findBedsByHostId(String hostId) {
        validateInputId(HOST_ID, hostId);
        return hosts
                .findById(hostId)
                .switchIfEmpty(handleHostNotFound(hostId))
                .flatMapMany(host -> beds
                        .findBedCosmosByHost(host)
                        .map(this::toDTO));
    }

    public Flux<BedDTO> findAllBeds() {
        return beds
                .findAll()
                .map(this::toDTO)
                .onErrorResume(ErrorHandler::handleError);
    }

    public Mono<BedDTO> updateBedStatus(String bedId, String hostId, boolean reserved) {
        validateInputId(HOST_ID, hostId);
        validateInputId(BED_ID, bedId);
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
                        .onErrorResume(ErrorHandler::handleError));
    }

    public Mono<BedDTO> deleteBedById(String bedId, String hostId) {
        validateInputId(HOST_ID, hostId);
        validateInputId(BED_ID, bedId);
        return hosts
                .findById(hostId)
                .switchIfEmpty(handleHostNotFound(hostId))
                .flatMap(host -> beds
                        .findById(bedId, new PartitionKey(host))
                        .switchIfEmpty(handleBedNotFound(bedId))
                        .flatMap(bed -> beds.deleteById(bedId, new PartitionKey(host))
                                .then(Mono.just(toDTO(bed))))
                        .onErrorResume(ErrorHandler::handleError));
    }

    private BedDTO toDTO(BedCosmos bed) {
        return new BedDTO(bed.getBedId(), bed.getHost(), bed.getReserved());
    }

}

