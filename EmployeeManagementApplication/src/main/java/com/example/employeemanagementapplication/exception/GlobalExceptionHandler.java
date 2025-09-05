package com.example.employeemanagementapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            EmployeeNotFoundException.class
    )
    public ResponseEntity<String> handledDuplicateEntity(EmployeeNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(
            DuplicateEmployeeException.class
    )
    public ResponseEntity<String> handledDuplicateEntity(DuplicateEmployeeException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(
            NoEmployeeFoundInDatabaseException.class
    )
    public ResponseEntity<String> handleNoEmployeeFoundInDatabase(NoEmployeeFoundInDatabaseException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(
            Exception.class
    )
    public ResponseEntity<String> handleAllExceptions(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }
}
