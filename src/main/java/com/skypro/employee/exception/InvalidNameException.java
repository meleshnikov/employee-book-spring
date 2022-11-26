package com.skypro.employee.exception;

public class InvalidNameException extends IllegalArgumentException {

    public InvalidNameException(String message) {
        super(message);
    }
}
