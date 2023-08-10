package com.noq.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vacancy {
    String id;
    String hostId;
    String hostName;
    Address address;
    String hostImg;
    String bedId;
}
