package com.noq.backend.models.cosmos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressCosmos {

    private String street;
    private String streetNum;
    private String postalCode;
    private String cityName;
}


