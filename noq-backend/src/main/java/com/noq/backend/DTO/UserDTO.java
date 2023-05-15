package com.noq.backend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDTO(String username, String password, boolean reservation) {
    @JsonCreator
    public UserDTO(
            @JsonProperty("username") String username,
            @JsonProperty("description") String password,
            @JsonProperty("reservation") boolean reservation) {
        this.username = username;
        this.password = password;
        this.reservation = reservation;
    }

    @Override
    public String username() {
        return username;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public boolean reservation() {
        return reservation;
    }
}
