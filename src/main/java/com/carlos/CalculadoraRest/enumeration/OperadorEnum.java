package com.carlos.CalculadoraRest.enumeration;

import com.carlos.CalculadoraRest.exception.CalculadoraException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public enum OperadorEnum {

    SOMA("+") {
        @Override
        public BigDecimal calcular(BigDecimal direita, BigDecimal esquerda) {
            return esquerda.add(direita);
        }
    },
    SUBTRACAO("-") {
        @Override
        public BigDecimal calcular(BigDecimal direita, BigDecimal esquerda) {
            return esquerda.subtract(direita);
        }
    },
    MULTIPLICACAO("*") {
        @Override
        public BigDecimal calcular(BigDecimal direita, BigDecimal esquerda) {
            return esquerda.multiply(direita);
        }
    },
    DIVISAO("/") {
        @Override
        public BigDecimal calcular(BigDecimal direita, BigDecimal esquerda) {
            try {
                return esquerda.divide(direita, 2, RoundingMode.UP);
            } catch (ArithmeticException erro) {
                throw new CalculadoraException("Erro ao tentar calcular a expressão: " + esquerda + "/" + direita + ".");
            }
        }
    };

    private final String codigo;

    private OperadorEnum(final String codigo) {
        this.codigo = codigo;
    }

    public static OperadorEnum getPorCodigo(final String codigo) {
        return Arrays.stream(OperadorEnum.values())
                .filter(operadorEnum -> operadorEnum.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(() -> new CalculadoraException("Tipo de operador não é reconhecido!"));
    }

    public String getCodigo() {
        return codigo;
    }

    public abstract BigDecimal calcular(BigDecimal direita, BigDecimal esquerda);
}
