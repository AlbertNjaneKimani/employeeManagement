package com.example.albert.employeemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
public class LimitExcededException extends RuntimeException{
        public LimitExcededException(String exception){
            super(exception);
        }
}
