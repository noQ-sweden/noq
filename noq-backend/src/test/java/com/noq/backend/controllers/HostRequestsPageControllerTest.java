package com.noq.backend.controllers;

import com.noq.backend.controllers.host.requests.HostRequestsPageController;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
class HostRequestsPageControllerTest {
    @Autowired
    HostRequestsPageController hostRequestsPageController;

    @Test
    void requestsViewModel() {
        String hostId = "6eee2453-711e-47f3-92d4-a26b255cc5a5";
        var block = hostRequestsPageController.requestsPage("");
        System.out.println(block);
    }
}
