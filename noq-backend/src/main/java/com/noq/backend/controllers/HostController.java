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
                .map(HostController::toHostDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/get-all-available")
    public List<HostDTO> getAllHostsWithBeds() {
        return hostService.getAllHostsWithBeds()
                .stream()
                .map(HostController::toHostDTO)
                .collect(Collectors.toList());
    }


    @PostMapping("/create-beds/{hostId}")
    public HostDTO createBeds(@RequestParam int numberOfBeds, @PathVariable String hostId){
        return toHostDTO(hostService.createBeds(hostId, numberOfBeds));
    }


    private static HostDTO toHostDTO(Host host) {

        List<BedDTO> bedDTOs = host.getBeds().stream()
                .map(bed -> new BedDTO(bed.getId(), null))
                .collect(Collectors.toList());

        return new HostDTO(
                host.getHostId(),
                host.getName(),
                host.getAddress(),
                host.getImage(),
                bedDTOs
        );
    }
}

