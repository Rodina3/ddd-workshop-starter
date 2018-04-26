package com.thoughtworks.workshop.ddd.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class EmailAlreadyRegisteredException extends ApplicationException {
    public EmailAlreadyRegisteredException(String message) {
        super(BAD_REQUEST, message);
    }
}
