package com.example.albert.employeemanagement.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ErrorResponse {
    public ErrorResponse(HttpStatus statusCode, String message, List<String> details) {
        super();
        this.statusCode = statusCode;
        this.message = message;
        this.details = details;
    }

    private final HttpStatus statusCode;
    private final String message;
    private final List<String> details;


}
