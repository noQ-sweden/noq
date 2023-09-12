package com.noq.backend.DTO;

import com.noq.backend.models.Address;
import com.noq.backend.models.cosmos.Bed;

import java.util.List;

public record HostCosmosDTO(String hostId, String name, Address address, String image, List<Bed> beds) {
}
