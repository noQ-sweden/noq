package com.noq.backend.exeptions;

public class NoReservationsException extends RuntimeException {
    public NoReservationsException(String message) {
        super(message);
    }
}