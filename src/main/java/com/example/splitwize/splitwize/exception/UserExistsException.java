package com.example.splitwize.splitwize.exception;

public class UserExistsException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UserExistsException() {
    }

    public UserExistsException(String message) {
        super(message);
    }
    
}