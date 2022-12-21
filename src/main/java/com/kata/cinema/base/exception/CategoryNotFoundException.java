package com.kata.cinema.base.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException (String message){
        super(message);
    }
}
