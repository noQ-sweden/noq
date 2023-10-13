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
        RequestsViewDTO block = requestsViewController.requestsViewModel("").block();
        System.out.println(block);
    }
}
