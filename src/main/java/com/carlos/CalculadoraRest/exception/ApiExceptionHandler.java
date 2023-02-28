package com.carlos.CalculadoraRest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = CalculadoraException.class)
    public ResponseEntity<?> handleCalculadoraException(final CalculadoraException erro) {
        return ResponseEntity.badRequest().body(erro.getMessage());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(final HttpMessageNotReadableException erro) {
        return ResponseEntity.badRequest().body(erro.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleException(final Exception erro) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro.getMessage());
    }
}

