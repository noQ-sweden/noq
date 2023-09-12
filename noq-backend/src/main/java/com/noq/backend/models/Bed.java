package com.noq.backend.models;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Bed {

    private String id;
    private Host host;
    private Boolean reserved;

    public Bed(String id, Host host){
        this.id = id;
        this.host = host;
        this.reserved = false;
    }

}