package com.noq.backend.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InitializeDataService {

    private UserService userService;
    private HostService hostService;

    @Autowired
    public InitializeDataService(UserService userService, HostService hostService) {
        this.userService = userService;
        this.hostService = hostService;
    }

    @PostConstruct
    public void initializeData() {
        userService.createUsers();
        hostService.createHosts();
    }
}
