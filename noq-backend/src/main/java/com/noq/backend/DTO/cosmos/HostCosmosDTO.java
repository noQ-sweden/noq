package com.noq.backend.DTO.cosmos;

import com.noq.backend.models.Address;
import com.noq.backend.models.cosmos.AddressCosmos;

import java.util.List;

public record HostCosmosDTO(String hostId, String name, AddressCosmos address, String image) {
}
