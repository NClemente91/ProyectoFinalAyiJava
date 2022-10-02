package com.ayi.rest.serv.app.controllers;

import com.ayi.rest.serv.app.dtos.response.ErrorResponseDTO;
import com.ayi.rest.serv.app.exceptions.BadRequestException;
import com.ayi.rest.serv.app.exceptions.NotFoundException;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {
            BadRequestException.class,
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<ErrorResponseDTO> badRequestHandlerException(HttpServletRequest request, RuntimeException exception) {
        ErrorResponseDTO error = ErrorResponseDTO
                .builder()
                    .exception(exception.getClass().getSimpleName())
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

        System.out.println(exception.toString());

        ErrorResponseDTO error = ErrorResponseDTO
                .builder()
                    .exception(exception.getClass().getSimpleName())
                    .message(exception.getMessage())
                    .path(request.getRequestURI())
                    .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> runtimeHandlerException(HttpServletRequest request, RuntimeException exception) {
        ErrorResponseDTO error = ErrorResponseDTO
                .builder()
                .exception(exception.getClass().getSimpleName())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
