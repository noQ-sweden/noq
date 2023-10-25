package com.noq.backend.services;

import com.azure.cosmos.models.PartitionKey;
import com.noq.backend.controllers.might_delete.DTOs.HostDTO;
import com.noq.backend.exceptions.HostNotFoundException;
import com.noq.backend.models.Host;
import com.noq.backend.repositories.HostRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HostService implements HostCosmosServiceI {
    private final HostRepository repositoryCosmos;

    //CREATE NEW HOST
    public Optional<HostDTO> create(HostDTO hostDTO) {
//        Host host = Host.create(hostDTO.name(), hostDTO.address(), hostDTO.image());

//        System.out.println("Creating Host with id: " + host.getHostId());
//
//        return repositoryCosmos.save(host)
//                .map(this::toDTO)
//                .onErrorResume(this::handleError);
        return null;
    }

    @Override
    public Host findByHostId(String id) {
        return repositoryCosmos.findByHostId(id)
                .orElseThrow(() -> new HostNotFoundException(id));
    }
    @Override
    public Host findById(String id) {
        return repositoryCosmos.findById(id)
                .orElseThrow(() -> new HostNotFoundException(id));
    }

    // GET HOST BY ID
    public Optional<HostDTO> findById(String id, String email) {
        System.out.println("Searching host: " + id);

        return repositoryCosmos.findById(id, new PartitionKey(email))
                .map(this::toDTO);
    }

    // GET ALL HOSTS
//    public Iterable<HostDTO> findAll() {
//        System.out.println("Listing all hosts...");
//        return repositoryCosmos.findAll();
//    }


    // ERROR HANDLING ####################################################################################
//    private Optional<HostDTO> handleError(Throwable error) {
//        return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
//                "Oops! Something went wrong!", error));
//    }

//    private Optional<HostDTO> handleNotFoundError(Throwable error) {
//        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found.", error));
//    }
    // ###################################################################################################

    // DTO CONVERTER
    private HostDTO toDTO(Host host) {
//        return new HostDTO(
//                host.getHostId(),
//                host.getName(),
//                host.getAddress(),
//                host.getImage()
//        );
        return null;
    }
}
