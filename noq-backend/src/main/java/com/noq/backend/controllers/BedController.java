package com.noq.backend.controllers;

import com.noq.backend.DTO.BedDTO;
import com.noq.backend.DTO.CreateBedRequest;
import com.noq.backend.services.BedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/bed")
public class BedController {
    private final BedService service;

    @PostMapping
    public Flux<ResponseEntity<BedDTO>> createBed(@RequestBody CreateBedRequest request) {
        return Flux.range(1, request.numberOfBeds())
                .flatMap(i -> service.createBed(request.hostId()))
                .map(bed -> {
//                    TODO: This line is for testing purposes...
//                    URI location = URI.create("/api/v1/bed/" + bed.id());
                    URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(bed.id())
                            .toUri();
                    return ResponseEntity.created(location).body(bed);
                });
    }

    @GetMapping
    public Flux<BedDTO> getBedsByHostId(@RequestParam String hostId) {
        return service
                .findBedsByHostId(hostId)
                .switchIfEmpty(Flux.empty());
    }
}
