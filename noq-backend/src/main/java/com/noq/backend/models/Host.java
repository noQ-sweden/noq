package com.noq.backend.models;
import jakarta.persistence.Column;
import lombok.*;
import jakarta.persistence.Id;

import java.util.*;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Host {

    private String hostId;
    private String name;
    private Address address;
    private String image;
    private List<Bed> beds = new ArrayList<>();

    public void addBed(Bed bed) {
        beds.add(bed);
        bed.setHost(this);
    }
}
