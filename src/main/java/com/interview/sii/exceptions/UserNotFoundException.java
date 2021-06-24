package com.interview.sii.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
