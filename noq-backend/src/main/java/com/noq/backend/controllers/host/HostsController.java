package com.noq.backend.controllers.host;

import com.noq.backend.models.Host;
import com.noq.backend.services.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
public class HostsController {

    @Autowired
    private HostService hostService;

    @GetMapping
    public List<Host> getAllHosts() {
        return hostService.allHosts();
    }

    @GetMapping("/city/{city}")
    public List<Host> getHostsForCity(@PathVariable("city") String city) {
        return hostService.findByHostCity(city);
    }
}
