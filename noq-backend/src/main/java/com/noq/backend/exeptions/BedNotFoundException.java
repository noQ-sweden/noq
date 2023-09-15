package com.noq.backend.exeptions;

public class BedNotFoundException extends RuntimeException {
    public BedNotFoundException(String message) {
        super(message);
    }
}
