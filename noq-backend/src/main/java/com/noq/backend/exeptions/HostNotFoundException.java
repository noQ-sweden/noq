package com.noq.backend.exeptions;

public class HostNotFoundException extends RuntimeException {
    public HostNotFoundException(String id) {
        super(String.format("Host not found. Id: %s", id));
    }
}
