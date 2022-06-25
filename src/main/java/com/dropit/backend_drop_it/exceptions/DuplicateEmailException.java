package com.dropit.backend_drop_it.exceptions;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException() {
        super();
    }

    public DuplicateEmailException(String message) {
        super(message);
    }

}
