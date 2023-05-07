package com.noq.backend.dto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

import java.util.Set;

public record BedDTO (int size, BigDecimal price,String picture) {
    @JsonCreator
    public BedDTO(
            @JsonProperty("size") int size,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("picture") String picture) {
        this.size = size;
        this.price = price;
        this.picture = picture;
    }

    @java.lang.Override
    public int size() {
        return size;
    }

    @java.lang.Override
    public BigDecimal price() {
        return price;
    }

    @java.lang.Override
    public String picture() {
        return picture;
    }

}
