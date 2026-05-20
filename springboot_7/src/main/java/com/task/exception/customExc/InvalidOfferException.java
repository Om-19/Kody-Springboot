package com.task.exception.customExc;

public class InvalidOfferException
        extends RuntimeException {

    public InvalidOfferException(String message) {
        super(message);
    }
}