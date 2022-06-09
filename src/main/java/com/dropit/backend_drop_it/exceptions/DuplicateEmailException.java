package com.dropit.backend_drop_it.exceptions;

public class DuplicateEmailException extends RuntimeException {
    private static final Long serialUID = 1L;

    public DuplicateEmailException() {
        super();
    }

    public DuplicateEmailException(String message) {
        super(message);
    }
}
