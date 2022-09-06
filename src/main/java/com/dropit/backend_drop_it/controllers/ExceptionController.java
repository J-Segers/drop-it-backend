package com.dropit.backend_drop_it.controllers;

import com.dropit.backend_drop_it.exceptions.DuplicateEmailException;
import com.dropit.backend_drop_it.exceptions.RecordNotFoundException;
import com.dropit.backend_drop_it.exceptions.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exceptionUser(RecordNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DuplicateEmailException.class)
    public ResponseEntity<Object> exceptionEmail(DuplicateEmailException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameAlreadyExistsException.class)
    public ResponseEntity<Object> userExistsException(UsernameAlreadyExistsException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
