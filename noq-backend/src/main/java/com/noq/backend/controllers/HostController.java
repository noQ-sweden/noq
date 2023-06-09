package com.noq.backend.controllers;

import com.noq.backend.DTO.AddressDTO;
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
public class HostController {

    private final HostService hostService;

    @Autowired
    public HostController(HostService hostService) {
        this.hostService = hostService;
    }


    @GetMapping("/get-all")
    public List<HostDTO> getAllHosts() {
        return hostService.getAllHosts()
                .stream()
                .map(HostController::hostDTO)
                .collect(Collectors.toList());
    }



    private static HostDTO hostDTO(Host host) {
        AddressDTO addressDTO = new AddressDTO(
                host.getAddress().getId(),
                host.getAddress().getStreet(),
                host.getAddress().getStreetNum(),
                host.getAddress().getPostalCode(),
                host.getAddress().getCityName()
        );

        return new HostDTO(
                host.getHostId(),
                host.getName(),
                addressDTO,
                host.getImage(),
                host.getBed()
        );
    }
}

