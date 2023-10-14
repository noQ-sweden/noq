package com.noq.backend.controllers.host.ReservationsViewController;

public record ReservationsViewDTO (
        String reservationId,
        String hostName,
        String hostImage,
        AddressDTO address
){
    public record AddressDTO(
            String street,
            String streetNum,
            String postalCode,
            String cityName
    ) {
    }
}
