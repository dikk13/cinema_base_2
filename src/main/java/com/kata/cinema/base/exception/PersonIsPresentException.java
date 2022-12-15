package com.kata.cinema.base.exception;

public class PersonIsPresentException extends RuntimeException{
    public PersonIsPresentException(String message) {
        super(message);
    }
}
