package com.noq.backend.models;

public enum Status {
    PENDING("Pending"),
    RESERVED("Reserved"),
    CANCELLED("Cancelled");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return name;
    }
}
