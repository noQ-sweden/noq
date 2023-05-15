package com.noq.backend.controllers;

import com.noq.backend.DTO.HostDTO;
import com.noq.backend.models.Host;
import com.noq.backend.services.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping("/getall")
    public List<HostDTO> getAllHosts() {
        return hostService.getAllHosts()
                .stream()
                .map(HostController::hostDTO)
                .collect(Collectors.toList());
    }

    private static HostDTO hostDTO(Host host) { // DO WE INCLUDE THE ID property? Unsure here!
        return new HostDTO(
                host.getHostId(), // null?
                host.getName(),
                host.getAddress(),
                host.getImage(),
                host.getBeds()
        );
    }
}

