package com.noq.backend.controllers;

import com.noq.backend.controllers.host.RequestsViewController.HostRequestsViewDTO;
import com.noq.backend.controllers.host.RequestsViewController.HostRequestsViewController;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
class HostRequestsViewControllerTest {
    @Autowired
    HostRequestsViewController hostRequestsViewController;

    @Test
    void requestsViewModel() {
        String hostId = "6eee2453-711e-47f3-92d4-a26b255cc5a5";
        var block = hostRequestsViewController.requestsViewModel("");
        System.out.println(block);
    }
}
