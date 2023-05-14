package com.raisetech.crudtask.domain.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String massage, Throwable cause) {
        super(massage, cause);
    }

    public ResourceNotFoundException(String massage) {
        super(massage);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
