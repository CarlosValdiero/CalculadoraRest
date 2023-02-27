package com.carlos.CalculadoraRest.exception;

public class NaoAutorizadoException extends RuntimeException {
    public NaoAutorizadoException() {
        super("Acesso não autorizado!");
    }
}
