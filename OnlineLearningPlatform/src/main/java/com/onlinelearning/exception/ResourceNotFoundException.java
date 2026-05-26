package com.onlinelearning.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8928818279695360960L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}
