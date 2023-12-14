package com.noq.backend.clients.user.availableHosts;

import com.noq.backend.services.HostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/available-hosts")
@RequiredArgsConstructor
public class UserAvailableHostsController {
    private final HostService hostService;
    String userId = "550e8400-e29b-41d4-a716-446655440010";

    @GetMapping
    public ResponseEntity<UserAvailableHostsDTO> getAllHosts() {
        var availableHosts = hostService.allHostsAvailable(userId).stream().map(UserAvailableHostsDTO.HostDTO::toDTO).toArray(UserAvailableHostsDTO.HostDTO[]::new);
        var dto = UserAvailableHostsDTO.builder()
                .availableHosts(availableHosts)
                .build();
        return ResponseEntity.ok(dto);
    }

}
