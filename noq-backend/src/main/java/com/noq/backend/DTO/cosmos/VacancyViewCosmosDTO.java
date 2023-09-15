package com.noq.backend.DTO.cosmos;

import com.noq.backend.models.Address;
import com.noq.backend.models.cosmos.HostCosmos;

import java.util.List;

public record VacancyViewCosmosDTO(Vacancy vacancies) {
    public record Vacancy(Host host, String bedId){};
    public record Host(String id, String name, Address address, String image){}

}
