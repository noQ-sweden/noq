
package com.noq.backend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.noq.backend.models.Address;
import com.noq.backend.models.Bed;

import java.util.HashSet;


public record HostDTO(String name, Address address, HashSet<Bed> bedIds) {

    @JsonCreator
    public HostDTO(
            @JsonProperty("name") String name,
            @JsonProperty("address") Address address,
            @JsonProperty("bedIds") HashSet<Bed> bedIds) {
        this.name = name;
        this.address = address;
        this.bedIds = bedIds;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public HashSet<Bed> bedIds() {
        return bedIds;
    }

}
