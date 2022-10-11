package com.ayi.rest.serv.app.exceptions.customsExceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

}
