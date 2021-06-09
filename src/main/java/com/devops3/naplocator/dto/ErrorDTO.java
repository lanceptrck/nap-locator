package com.devops3.naplocator.dto;

import com.devops3.naplocator.exception.ExceptionResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDTO extends GenericDTO{

    private ExceptionResponse error;

    public ErrorDTO() {
    }

    public ExceptionResponse getError() {
        return error;
    }

    public void setError(ExceptionResponse error) {
        this.error = error;
    }
}
