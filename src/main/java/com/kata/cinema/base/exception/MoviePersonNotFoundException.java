package com.kata.cinema.base.exception;

public class MoviePersonNotFoundException extends RuntimeException{
    public MoviePersonNotFoundException(String message) {
        super(message);
    }
}
