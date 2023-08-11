package com.noq.backend.controllers;

import com.noq.backend.DTO.AddressDTO;
import com.noq.backend.DTO.BedDTO;
import com.noq.backend.DTO.HostDTO;
import com.noq.backend.models.Host;
import com.noq.backend.services.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/host")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HostPageController {

    private final HostService hostService;

    @Autowired
    public HostPageController(HostService hostService) {
        this.hostService = hostService;
    }


    @GetMapping("/get-all")
    public List<HostDTO> getAllHosts() {
        return hostService.getAllHosts()
                .stream()
                .map(HostPageController::toHostDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/get-all-available")
    public List<HostDTO> getAllHostsWithBeds() {
        return hostService.getAllHostsWithBeds()
                .stream()
                .map(HostPageController::toHostDTO)
                .collect(Collectors.toList());
    }



    private static HostDTO toHostDTO(Host host) {

        AddressDTO addressDTO = new AddressDTO(host.getAddress().getStreet(),
                host.getAddress().getStreetNum(), host.getAddress().getPostalCode(),
                host.getAddress().getCityName());

        List<BedDTO> bedDTOs = host.getBeds().stream()
                .map(bed -> new BedDTO(bed.getId(), null))
                .collect(Collectors.toList());

        return new HostDTO(
                host.getHostId(),
                host.getName(),
                addressDTO,
                host.getImage(),
                bedDTOs
        );
    }
}

