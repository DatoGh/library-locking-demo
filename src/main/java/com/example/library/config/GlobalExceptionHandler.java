package com.example.library.config;

import com.example.library.exception.InsufficientStockException;
import com.example.library.exception.RetryExhaustedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.InsufficientResourcesException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientResourcesException.class)
    public ResponseEntity<Object> handleInsufficientStockException() {
        return new ResponseEntity<>("Not enough stock available", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<Object> handleInterruptedException() {
        return new ResponseEntity<>("Operation interrupted", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RetryExhaustedException.class)
    public ResponseEntity<Object> handleRetryExhaustedException(RetryExhaustedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
