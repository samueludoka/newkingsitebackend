package org.example.exception;

public class UserAlreadyExist extends Throwable {
    public UserAlreadyExist(String message) {
        super(message);
    }
}
