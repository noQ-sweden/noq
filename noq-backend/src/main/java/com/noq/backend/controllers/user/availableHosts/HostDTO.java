package com.noq.backend.controllers.user.availableHosts;

import com.noq.backend.models.Host;
import lombok.Builder;

import java.util.UUID;

record HostDTO(UUID hostId,
               String name,
               String address1,
               String address2,
               String city,
               String addressPostcode,
               String email,
               int countOfAvailablePlaces,
               int totalPlaces) {
    @Builder
    public HostDTO {
    }

    public static HostDTO toDTO(Host host) {
        return HostDTO.builder()
                .hostId(host.getHostId())
                .name(host.getName())
                .address1(host.getAddress_1())
                .address2(host.getAddress_2())
                .city(host.getCity())
                .addressPostcode(host.getAddressPostcode())
                .email(host.getEmail())
                .countOfAvailablePlaces(host.getCountOfAvailablePlaces())
                .totalPlaces(host.getTotalPlaces())
                .build();
    }
}
