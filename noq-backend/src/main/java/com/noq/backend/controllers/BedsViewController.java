package com.noq.backend.controllers;

import com.noq.backend.services.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/bed")
public class BedsViewController {

    private final HostService hostService;

    @Autowired
    public BedsViewController(HostService hostService) {
        this.hostService = hostService;
    }



    @PutMapping("/create-beds/{hostId}")
    public ResponseEntity<String> createBeds(@RequestParam int numberOfBeds, @PathVariable String hostId){
        hostService.createBeds(hostId, numberOfBeds);
        String responseMessage = numberOfBeds + " beds created for host " + hostId;
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

}
