package com.example.dripchipsystem;

import lombok.AllArgsConstructor;
import org.springframework.core.NestedRuntimeException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    protected String handle(ConstraintViolationException ex) {
        return ex.getLocalizedMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    protected String handle(EntityNotFoundException ex) {
        return ex.getLocalizedMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(ConversionFailedException.class)
    protected String handle(ConversionFailedException ex) {
        return ex.getLocalizedMessage();
    }
}
