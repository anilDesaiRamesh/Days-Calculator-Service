package com.qantas.insurance.exception;

public class InvalidInputValException extends RuntimeException {

    private String message;

    public InvalidInputValException(String message) {
        super(message);
        this.message = message;
    }
}
