package com.noq.backend.models;

public enum Status {
    WAITING("Waiting"),
    ACCEPTED("Accepted"),
    DECLINED("Decline");

    private final String name;

    Status(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
