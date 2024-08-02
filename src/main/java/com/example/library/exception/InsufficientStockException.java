package com.example.library.exception;

import javax.naming.InsufficientResourcesException;

public class InsufficientStockException extends InsufficientResourcesException {
    public InsufficientStockException(String message) {
        super(message);
    }
}