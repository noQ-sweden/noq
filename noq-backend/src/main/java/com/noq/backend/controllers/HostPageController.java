package com.noq.backend.controllers;

import com.noq.backend.DTO.HostPageDTO;
import com.noq.backend.DTO.UserPageDTO;
import com.noq.backend.models.Host;
import com.noq.backend.models.User;
import com.noq.backend.services.HostService;
import com.noq.backend.services.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/host-page")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HostPageController {

    private final HostService hostService;


    @GetMapping("/{hostId}")
    public HostPageDTO getHostById(@PathVariable String hostId) {
        Host host  = hostService.getHostById(hostId);
        return toDTO(host);
    }


    private static HostPageDTO toDTO (Host host) {
        return new HostPageDTO(host.getHostId(), host.getName());
    }

}
