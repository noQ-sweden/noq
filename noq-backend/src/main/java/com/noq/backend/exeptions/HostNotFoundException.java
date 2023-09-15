package com.noq.backend.exeptions;

public class HostNotFoundException extends RuntimeException {
    public HostNotFoundException(String message) {
        super(message);
    }
}
