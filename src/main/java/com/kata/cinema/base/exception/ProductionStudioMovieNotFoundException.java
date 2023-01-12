package com.kata.cinema.base.exception;

public class ProductionStudioMovieNotFoundException extends RuntimeException {
    public ProductionStudioMovieNotFoundException(String message) {
        super(message);
    }
}
