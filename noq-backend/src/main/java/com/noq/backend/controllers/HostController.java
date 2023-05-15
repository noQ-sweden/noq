package com.noq.backend.controllers;

import com.noq.backend.dto.HostDTO;
import com.noq.backend.services.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        return hostService.getAllHosts();
    }

}

