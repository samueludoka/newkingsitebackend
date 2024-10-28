package org.example.exception;

public class WalletExistException extends RuntimeException {
    public WalletExistException(String message) {
        super(message);
    }
}
