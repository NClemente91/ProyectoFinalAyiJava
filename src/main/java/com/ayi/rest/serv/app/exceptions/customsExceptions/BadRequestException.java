package com.ayi.rest.serv.app.exceptions.customsExceptions;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String message) {
        super(message);
    }

}
