package com.noq.backend.dto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public record AddressDTO (String street, String city, String zipCode, String region) {
    @JsonCreator
    public AddressDTO(@JsonProperty("street") String street,
                  @JsonProperty("city") String city,
                  @JsonProperty("zipCode") String zipCode,
                  @JsonProperty("region") String region) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.region = region;
    }
    @java.lang.Override
    public String street() {
        return street;
    }

    @java.lang.Override
    public String city() {
        return city;
    }
    @java.lang.Override
    public String zipCode() {
        return zipCode;
    }
    @java.lang.Override
    public String region() {
        return region;
    }
}
