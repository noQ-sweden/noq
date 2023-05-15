package com.noq.backend.dto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record BedDTO (double price, HostDTO hostDTO) {
    @JsonCreator
    public BedDTO(@JsonProperty("price") double price,
            @JsonProperty("hostDTO") HostDTO hostDTO) {
        this.price = price;
        this.hostDTO = hostDTO;
    }
    @java.lang.Override
    public double price() {
        return price;
    }

    @java.lang.Override
    public HostDTO hostDTO() {
        return hostDTO;
    }
}
