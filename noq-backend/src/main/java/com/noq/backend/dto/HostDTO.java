package com.noq.backend.DTO;

import com.noq.backend.models.Address;
import java.util.UUID;


public record HostDTO(UUID hostId, String name, Address address, String image, Long beds) {

}
