package com.noq.backend.controllers;

import com.noq.backend.DTO.RequestsViewDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
class RequestsViewControllerTest {
    @Autowired
    RequestsViewController requestsViewController;

    @Test
    void requestsViewModel() {
        String hostId = "6eee2453-711e-47f3-92d4-a26b255cc5a5";
        RequestsViewDTO block = requestsViewController.requestsViewModel(hostId).block();
        System.out.println(block);
    }
}
