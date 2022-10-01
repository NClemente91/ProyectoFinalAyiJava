package com.ayi.rest.serv.app.exceptions;

public class BadRequestException extends RuntimeException{

    private final Integer code = 400;

    public BadRequestException(String message) {
        super(message);
    }

}
