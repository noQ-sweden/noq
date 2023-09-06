package com.noq.backend.services;

import com.azure.cosmos.models.PartitionKey;
import com.noq.backend.DTO.HostCosmosDTO;
import com.noq.backend.models.HostCosmos;
import com.noq.backend.repository.HostRepositoryCosmos;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class HostCosmosService {

    private final HostRepositoryCosmos repositoryCosmos;

    //CREATE NEW HOST
    public Mono<HostCosmosDTO> create(HostCosmosDTO request) {
        HostCosmos host = new HostCosmos(
                request.name(),
                request.address(),
                request.image()
                );

        System.out.println("Creating Host with id: " + host.getHostId());

        return repositoryCosmos.save(host)
                .map(this::toDTO)
                .onErrorResume(this::handleError);
    }

    // GET HOST BY ID
    public Mono<HostCosmosDTO> findById(String id, String email) {
        System.out.println("Searching user: " + id);

        return repositoryCosmos.findById(id, new PartitionKey(email))
                .map(this::toDTO)
                .onErrorResume(this::handleNotFoundError);
    }

    // GET ALL HOSTS
    public Flux<HostCosmosDTO> findAll() {
        System.out.println("Listing all users...");
        return repositoryCosmos.findAll().map(this::toDTO)
                .onErrorResume(this::handleError);
    }


    // ERROR HANDLING ####################################################################################
    private Mono<HostCosmosDTO> handleError(Throwable error) {
        return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Oops! Something went wrong, and i blame the user!", error));
    }

    private Mono<HostCosmosDTO> handleNotFoundError(Throwable error) {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found.", error));
    }
    // ###################################################################################################

    // DTO CONVERTER
    private HostCosmosDTO toDTO(HostCosmos host) {
        return new HostCosmosDTO(
                host.getHostId(),
                host.getName(),
                host.getAddress(),
                host.getImage(),
                host.getBeds()
        );
    }
}
