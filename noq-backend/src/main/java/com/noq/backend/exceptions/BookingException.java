package com.noq.backend.exceptions;

public class BookingException extends RuntimeException {
    public BookingException(String bookingId, String errorMessage) {
        super(String.format("Exception for booking with Id: %s with message: %s", bookingId, errorMessage));
    }
}
