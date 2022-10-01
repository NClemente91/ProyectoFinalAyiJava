package com.ayi.rest.serv.app.exceptions;

public class NotFoundException extends RuntimeException {

    private final Integer code = 404;

    public NotFoundException(String message) {
        super(message);
    }

}
