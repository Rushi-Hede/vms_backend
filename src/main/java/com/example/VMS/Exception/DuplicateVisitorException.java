package com.example.VMS.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class DuplicateVisitorException extends RuntimeException {
    public DuplicateVisitorException(String message) {
        super(message);
    }
}
