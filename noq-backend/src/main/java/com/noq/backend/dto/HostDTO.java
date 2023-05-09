package com.noq.backend.dto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public record HostDTO (String name, AddressDTO addressDTO) {
    @JsonCreator
    public HostDTO(@JsonProperty("name") String name,
                  @JsonProperty("addressDTO") AddressDTO addressDTO) {
        this.name = name;
        this.addressDTO = addressDTO;
    }
    @java.lang.Override
    public String name() {
        return name;
    }

    @java.lang.Override
    public AddressDTO addressDTO() {
        return addressDTO;
    }

}
