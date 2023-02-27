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

    @ExceptionHandler(value = NaoAutorizadoException.class)
    public ResponseEntity<?> handleNaoAutorizadoException(final NaoAutorizadoException erro) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro.getMessage());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(final HttpMessageNotReadableException erro) {
        return ResponseEntity.badRequest().body(erro.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleException(final Exception erro) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro.getMessage());
    }
}

