package com.noq.backend.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record ReservationDTO(BedDTO bedDTO, UserDTO userDTO, LocalDateTime reservedTime) {
    @JsonCreator
    public ReservationDTO(
            @JsonProperty("bedDTO") BedDTO bedDTO,
            @JsonProperty("userDTO") UserDTO userDTO,
            @JsonProperty("bedIds") LocalDateTime reservedTime) {
        this.bedDTO = bedDTO;
        this.userDTO = userDTO;
        this.reservedTime = reservedTime;
    }

    @Override
    public BedDTO bedDTO() {
        return bedDTO;
    }

    @Override
    public UserDTO userDTO() {
        return userDTO;
    }

    @Override
    public LocalDateTime reservedTime() {
        return reservedTime;
    }
}
