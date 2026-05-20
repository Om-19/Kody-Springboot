package com.task.exception.customExc;

public class ProductAlreadyDeletedException
        extends RuntimeException {

    public ProductAlreadyDeletedException(
            String message) {
        super(message);
    }
}
