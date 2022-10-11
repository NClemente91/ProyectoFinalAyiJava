package com.ayi.rest.serv.app.exceptions;

import com.ayi.rest.serv.app.exceptions.customsExceptions.BadRequestException;
import com.ayi.rest.serv.app.exceptions.customsExceptions.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,
            MethodArgumentTypeMismatchException.class,
            MethodArgumentNotValidException.class,
            DataIntegrityViolationException.class,
            HttpMessageNotReadableException.class,
    })
    @ResponseBody
    public ResponseEntity<ErrorMessage> badRequestHandlerException(HttpServletRequest request, Exception exception) {
        ErrorMessage error = ErrorMessage
                .builder()
                    .exception(exception.getClass().getSimpleName())
                    .message(exception.getMessage())
                    .path(request.getRequestURI())
                    .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            NotFoundException.class,
            IllegalArgumentException.class,
    })
    @ResponseBody
    public ResponseEntity<ErrorMessage> notFoundHandlerException(HttpServletRequest request, Exception exception) {
        ErrorMessage error = ErrorMessage
                .builder()
                    .exception(exception.getClass().getSimpleName())
                    .message(exception.getMessage())
                    .path(request.getRequestURI())
                    .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> runtimeHandlerException(HttpServletRequest request, Exception exception) {
        ErrorMessage error = ErrorMessage
                .builder()
                .exception(exception.getClass().getSimpleName())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
