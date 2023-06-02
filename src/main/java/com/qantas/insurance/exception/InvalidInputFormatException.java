package com.qantas.insurance.exception;

public class InvalidInputFormatException extends RuntimeException {
    String message;

    public InvalidInputFormatException(String message) {
        super(message);
        this.message = message;
    }
}
