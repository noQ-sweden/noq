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
        String hostId = "efe102ca-e3e9-42b4-a722-f0fbc0306b7a";
        RequestsViewDTO block = requestsViewController.requestsViewModel(hostId).block();
        System.out.println(block);
    }
}
