package com.interview.sii.exceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
