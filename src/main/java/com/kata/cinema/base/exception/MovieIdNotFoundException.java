package com.kata.cinema.base.exception;

public class MovieIdNotFoundException extends RuntimeException{
    public MovieIdNotFoundException(String message) {
        super(message);
    }
}
