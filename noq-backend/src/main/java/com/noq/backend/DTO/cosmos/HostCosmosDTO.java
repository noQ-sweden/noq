package com.noq.backend.DTO.cosmos;

import com.noq.backend.models.Address;

import java.util.List;

public record HostCosmosDTO(String hostId, String name, Address address, String image, List<com.noq.backend.models.cosmos.BedCosmos> beds) {
}
