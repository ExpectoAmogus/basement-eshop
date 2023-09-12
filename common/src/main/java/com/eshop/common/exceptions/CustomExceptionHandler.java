package com.eshop.common.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        String requestUri = ((ServletWebRequest)request).getRequest().getRequestURI();
        ExceptionMessage exceptionMessage = new ExceptionMessage(ex.getMessage(), requestUri);
        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
        String requestUri = ((ServletWebRequest)request).getRequest().getRequestURI();
        ExceptionMessage exceptionMessage = new ExceptionMessage(ex.getMessage(), requestUri);
        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
