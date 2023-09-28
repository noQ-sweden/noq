package com.noq.backend.DTO.cosmos;

public record VacancyViewCosmosDTO(Vacancy vacancies) {
    public record Vacancy(Host host, String bedId){};
    public record Host(String id, String name, com.noq.backend.models.cosmos.AddressCosmos address, String image){}

}
