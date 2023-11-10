package com.noq.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Data
public class Host {
    private String hostId;
    private String name;
    private Address[] address;
    private String image;

    @Data
    @AllArgsConstructor
    public static class Address {
        private String id;
        private String addressId;
        private String street;
        private String streetNum;
        private String postalCode;
        private String cityName;

        public static Address create(String street, String streetNum, String postalCode , String cityName) {
            return new Address(
                    UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(),
                    street,
                    streetNum,
                    postalCode,
                    cityName
            );
        }
    }

    public static Host create(String name, List<Address> address, String image) {
        return new Host(
                UUID.randomUUID().toString(),
                name,
                address.toArray(Address[]::new),
                image
        );
    }
}
