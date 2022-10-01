package com.ayi.rest.serv.app.controllers;

import com.ayi.rest.serv.app.dtos.response.ErrorResponseDTO;
import com.ayi.rest.serv.app.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> runtimeHandlerException(HttpServletRequest request, RuntimeException exception) {
        ErrorResponseDTO error = ErrorResponseDTO
                .builder()
                    .exception(exception.getClass().getSimpleName())
                    .statusCode(500)
                    .message(exception.getMessage())
                    .path(request.getRequestURI())
                    .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {
            NotFoundException.class,
            IllegalArgumentException.class,
    })
    public ResponseEntity<ErrorResponseDTO> notFoundHandlerException(HttpServletRequest request, NotFoundException exception) {

        ErrorResponseDTO error = ErrorResponseDTO
                .builder()
                    .exception(exception.getClass().getSimpleName())
                    .statusCode(404)
                    .message(exception.getMessage())
                    .path(request.getRequestURI())
                    .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}
