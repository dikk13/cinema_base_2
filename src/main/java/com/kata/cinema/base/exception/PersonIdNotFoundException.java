package com.kata.cinema.base.exception;

public class PersonIdNotFoundException extends RuntimeException{
    public PersonIdNotFoundException(String message) {
        super(message);
    }
}
