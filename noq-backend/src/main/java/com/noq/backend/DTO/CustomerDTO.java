package com.noq.backend.DTO;

public record CustomerDTO (String id, String customerId, String firstName, String lastName, String email, String phone, Address address){
    public record Address(String street, String city, String zip, String country) {}
}
