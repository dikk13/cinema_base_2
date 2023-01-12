package com.kata.cinema.base.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(String message) {
        super(message);
    }
}
