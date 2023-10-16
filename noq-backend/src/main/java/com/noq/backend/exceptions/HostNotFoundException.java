package com.noq.backend.exceptions;

public class HostNotFoundException extends RuntimeException {
    public HostNotFoundException(String id) {
        super(String.format("Host not found. Id: %s", id));
    }
}
