package com.noq.backend.controllers;

import com.noq.backend.models.CreateReservation;
import com.noq.backend.models.UpdateName;
import com.noq.backend.services.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/settings")
public class SettingsViewController {

    private final HostService hostService;

    @Autowired
    public SettingsViewController(HostService hostService) {
        this.hostService = hostService;
    }



    @PutMapping("/create-beds/{hostId}")
    public ResponseEntity<String> createBeds(@RequestParam int numberOfBeds, @PathVariable String hostId){
        hostService.createBeds(hostId, numberOfBeds);
        String responseMessage = numberOfBeds + " beds created for host " + hostId;
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @PutMapping("/update-name/")
    public ResponseEntity<String> updateName(@RequestBody UpdateName updateName){
        hostService.updateName(updateName);
        String responseMessage = "name was updated";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }
}
