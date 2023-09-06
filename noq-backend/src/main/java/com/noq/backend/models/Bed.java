package com.noq.backend.models;


import lombok.Data;
import lombok.NoArgsConstructor;
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
