package com.noq.backend.controllers.host.BedsViewController;

//import com.noq.backend.services.BedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

//@RestController
//@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//@RequestMapping("/api/host/bed")
public class BedsViewController {
//    private final BedService service;

//    @PostMapping
//    public Flux<ResponseEntity<BedDTO>> createBed(@RequestBody CreateBedRequest request) {
//        return Flux.range(1, request.numberOfBeds())
//                .flatMap(i -> service.createBed(request.hostId()))
//                .map(bed -> {
////                    TODO: This line is for testing purposes...
////                    URI location = URI.create("/api/v1/bed/" + bed.id());
//                    URI location = ServletUriComponentsBuilder
//                            .fromCurrentRequest()
//                            .path("/{id}")
//                            .buildAndExpand(bed.id())
//                            .toUri();
//                    return ResponseEntity.created(location).body(bed);
//                });
//    }

//    @GetMapping
//    public Flux<BedDTO> getBedsByHostId(@RequestParam String hostId) {
//        return service
//                .findBedsByHostId(hostId)
//                .switchIfEmpty(Flux.empty());
//    }
}
