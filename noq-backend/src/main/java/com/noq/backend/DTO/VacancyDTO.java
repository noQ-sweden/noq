package com.noq.backend.DTO;

import com.noq.backend.models.Address;

public record VacancyDTO(
        String hostId,
        String hostName,
        String hostImage,
        Address address,
        String bedId
) {
}
