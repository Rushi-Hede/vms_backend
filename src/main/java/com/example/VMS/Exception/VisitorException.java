package com.example.VMS.Exception;

import org.springframework.http.HttpStatus;

public class VisitorException {

    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;

    public VisitorException(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }
}
