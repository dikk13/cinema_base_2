package com.kata.cinema.base.exception;

public class PasswordUncoincidenceException extends RuntimeException {
    public PasswordUncoincidenceException (String message) {
        super(message);
    }
}
