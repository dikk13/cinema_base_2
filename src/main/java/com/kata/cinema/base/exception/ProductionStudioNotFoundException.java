package com.kata.cinema.base.exception;

public class ProductionStudioNotFoundException extends RuntimeException {
    public ProductionStudioNotFoundException(String message) {
        super(message);
    }
}
