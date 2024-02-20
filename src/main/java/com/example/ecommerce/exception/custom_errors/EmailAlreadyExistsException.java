package com.example.ecommerce.exception.custom_errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends RuntimeException   {
    private String message;

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
