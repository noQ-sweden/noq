package com.noq.backend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "*", allowedHeaders ="*")
public class ReservationController {

    @GetMapping("/hello")
    public String testHello() {
        return "hello";
    }
}
