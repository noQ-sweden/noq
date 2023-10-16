package com.noq.backend.services;

import com.azure.cosmos.models.PartitionKey;
import com.noq.backend.controllers.might_delete.DTOs.HostDTO;
import com.noq.backend.exceptions.HostNotFoundException;
import com.noq.backend.models.Host;
import com.noq.backend.repositories.HostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class HostService implements HostCosmosServiceI {
    private final HostRepository repositoryCosmos;

    //CREATE NEW HOST
    public Mono<HostDTO> create(HostDTO request) {
        Host host = new Host(
                request.name(),
                request.address(),
                request.image()
                );

        System.out.println("Creating Host with id: " + host.getHostId());

        return repositoryCosmos.save(host)
                .map(this::toDTO)
                .onErrorResume(this::handleError);
    }

    @Override
    public Mono<Host> findByHostId(String id) {
        return repositoryCosmos.findByHostId(id)
                .switchIfEmpty(Mono.error(new HostNotFoundException(id)));
    }
    @Override
    public Mono<Host> findById(String id) {
        return repositoryCosmos.findById(id)
                .switchIfEmpty(Mono.error(new HostNotFoundException(id)));
    }

    // GET HOST BY ID
    public Mono<HostDTO> findById(String id, String email) {
        System.out.println("Searching host: " + id);

        return repositoryCosmos.findById(id, new PartitionKey(email))
                .map(this::toDTO)
                .onErrorResume(this::handleNotFoundError);
    }

    // GET ALL HOSTS
    public Flux<HostDTO> findAll() {
        System.out.println("Listing all hosts...");
        return repositoryCosmos.findAll().map(this::toDTO)
                .onErrorResume(this::handleError);
    }


    // ERROR HANDLING ####################################################################################
    private Mono<HostDTO> handleError(Throwable error) {
        return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Oops! Something went wrong!", error));
    }

    private Mono<HostDTO> handleNotFoundError(Throwable error) {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found.", error));
    }
    // ###################################################################################################

    // DTO CONVERTER
    private HostDTO toDTO(Host host) {
        return new HostDTO(
                host.getHostId(),
                host.getName(),
                host.getAddress(),
                host.getImage()
        );
    }
}
