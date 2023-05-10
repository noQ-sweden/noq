package com.noq.backend.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.noq.backend.services.BedService;
import com.noq.backend.dto.*;
import java.util.List;

@RestController
@RequestMapping("/api/bed")
@CrossOrigin(origins = "*", allowedHeaders ="*")
public class BedController {


    //Refactor and so that it is reservation isntead of Bed.
    private final BedService bedService;

    public BedController(BedService bedService) {
        this.bedService = bedService;
    }
/*
    @GetMapping
    public List<BedDTO> getAllBeds() {
        return bedService.getAllBeds();
    }

    @GetMapping("/{id}")
    public BedDTO getBedById(@PathVariable Long id) {
        return bedService.getBedById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BedDTO createBed(@RequestBody BedDTO bedDTO) {
        return bedService.createBed(bedDTO);
    }

    @PutMapping("/{id}")
    public BedDTO updateBed(@PathVariable Long id, @RequestBody BedDTO bedDTO) {
        return bedService.updateBed(id, bedDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBed(@PathVariable Long id) {
        bedService.deleteBed(id);
    }*/
}
