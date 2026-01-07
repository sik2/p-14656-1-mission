package com.back.exception;

public class PostNotFoundException extends DomainException {
    public PostNotFoundException(String message) {
        super("404", message);
    }
}

