package com.devops3.naplocator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class EmptyFieldException extends RuntimeException {

    public EmptyFieldException(String message) {
        super(message);
    }
}
