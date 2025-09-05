package com.example.employeemanagementapplication.exception;

public class NoEmployeeFoundInDatabaseException extends RuntimeException {
    public NoEmployeeFoundInDatabaseException(String message) {
        super(message);
    }
}
