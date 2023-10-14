package com.noq.backend.DTO;

import com.noq.backend.models.Address;

public record VacancyViewDTO(Vacancy vacancies) {
    public record Vacancy(
            Host host,
            String bedId
    ){};

    public record Host(
            String id,
            String name,
            Address address,
            String image
    ){}

}
