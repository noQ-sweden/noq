package com.noq.backend.DTO;

import com.noq.backend.models.Address;
import com.noq.backend.models.Bed;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record HostDTO (String hostId, String name, Address address, String image, List<BedDTO> beds){
}
