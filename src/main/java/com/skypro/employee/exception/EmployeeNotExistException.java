package com.skypro.employee.exception;

public class EmployeeNotExistException extends RuntimeException {

    public EmployeeNotExistException(String message) {
        super(message);
    }
}
