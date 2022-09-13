package com.kata.cinema.base.exception;

public class GenreIdNotFoundException extends RuntimeException {
    public GenreIdNotFoundException(String message) {
        super(message);
    }
}
