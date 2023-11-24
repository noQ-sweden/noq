package com.noq.backend.controllers.user.availableHosts;

import com.noq.backend.services.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/available-hosts")
public class UserAvailableHostsController {

    @Autowired
    private HostService hostService;

    @GetMapping
    public UserAvailableHostsDTO getAllHosts() {
        return new UserAvailableHostsDTO(hostService.allHosts()
                .stream()
                .map(HostDTO::toDTO)
                .toList());
    }
}
