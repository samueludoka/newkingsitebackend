package org.example.exception;

public class WithdrawNotFoundException extends RuntimeException {
    public WithdrawNotFoundException(String message) {
        super(message);
    }
}
