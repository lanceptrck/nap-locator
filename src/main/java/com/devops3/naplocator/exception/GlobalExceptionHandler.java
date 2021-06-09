package com.devops3.naplocator.exception;


import com.devops3.naplocator.dto.ErrorDTO;
import com.devops3.naplocator.dto.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@EnableWebMvc
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<?> handleEmptyFieldException(EmptyFieldException ex) {
        ErrorDTO dto = new ErrorDTO();
        dto.setStatus(Status.FAILURE);
        dto.setError(buildExceptionResponse(ex, "EMPTY FIELD"));
        dto.setResponseCode(HttpStatus.NOT_ACCEPTABLE.value());

        // 406
        return new ResponseEntity<>(dto, HttpStatus.NOT_ACCEPTABLE);
    }

    private ExceptionResponse buildExceptionResponse(Exception ex, String errorCode) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorMessage(ex.getMessage());
        response.setErrorCode(errorCode);
        response.setTimestamp(LocalDateTime.now());
        return response;
    }


}
