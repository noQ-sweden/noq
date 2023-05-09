package com.noq.backend.models;

public enum Role {
    ADMINSTRATOR("Administrator"),
    SHELTER("Shelter"),
    CLIENT("Client");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
