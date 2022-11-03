package com.kata.cinema.base.exception;

public class NewsIdNotFoundException extends RuntimeException{
    public NewsIdNotFoundException(String message) {
        super(message);
    }
}
