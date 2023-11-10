package com.noq.backend.services;

import com.noq.backend.exceptions.HostNotFoundException;
import com.noq.backend.models.Host;
import com.noq.backend.repositories.HostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HostService implements HostServiceI {
    private final HostRepository hostRepository;

    //CREATE NEW HOST
    /* TODO
    public Optional<HostDTO> create(HostDTO hostDTO) {
        return null;
    }*/

    @Override
    public Host findByHostId(String id) {
        return hostRepository.findByHostId(id)
                .orElseThrow(() -> new HostNotFoundException(id));
    }
    @Override
    public Host findById(String id) {
        throw new RuntimeException("findById Not Implemented");
    }

/*  TODO GET HOST BY ID */

    // TODO GET ALL HOSTS

    // DTO CONVERTER
/*    private HostDTO toDTO(Host host) {
        return new HostDTO(
                host.getHostId(),
                host.getName(),
                host.getAddress(),
                host.getImage()
        );
        return null;
    }*/
}
