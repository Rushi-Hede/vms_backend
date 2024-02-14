package com.example.VMS.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VisitorExceptionHandler {

    @ExceptionHandler(value = {VisitorNotFoundException.class})
    public ResponseEntity<Object> handleVisitorNotFoundException(VisitorNotFoundException visitorNotFoundException) {
        VisitorException visitorException = new VisitorException(
                visitorNotFoundException.getMessage(),
                visitorNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(visitorException, HttpStatus.NOT_FOUND);
    }
}
