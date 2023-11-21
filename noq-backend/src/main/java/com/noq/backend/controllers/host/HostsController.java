package com.noq.backend.controllers.host;

import com.noq.backend.services.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hosts")
public class HostsController {

    @Autowired
    private HostService hostService;

    @GetMapping
    public HostsListPageDTO getAllHosts() {
        return new HostsListPageDTO(hostService.allHosts());
    }

    @GetMapping("/city/{city}")
    public HostsListPageDTO getHostsForCity(@PathVariable("city") String city) {
        return new HostsListPageDTO(hostService.findByHostCity(city));
    }
}
