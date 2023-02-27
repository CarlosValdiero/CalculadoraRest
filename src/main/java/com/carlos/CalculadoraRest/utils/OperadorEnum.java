package com.carlos.CalculadoraRest.utils;

import com.carlos.CalculadoraRest.exception.CalculadoraException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
                return esquerda.divide(direita, 2, RoundingMode.HALF_UP);
            } catch (ArithmeticException erro) {
                throw new CalculadoraException("Erro ao tentar calcular a expressão: "+esquerda+"/"+direita+".");
            }
        }
    };

    private String codigo;

    private OperadorEnum(final String codigo) {
        this.codigo = codigo;
    }

    public static OperadorEnum getPorCodigo(final String codigo) {
        return Arrays.asList(OperadorEnum.values())
                .stream().filter(operadorEnum -> operadorEnum.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(() -> new CalculadoraException("Tipo de operador não é reconhecido!"));
    }

    private String getCodigo() {
        return codigo;
    }

    public static List<String> getCodigos() {
        return Arrays.asList(OperadorEnum.values())
                .stream().map(OperadorEnum::getCodigo)
                .collect(Collectors.toList());
    }

    public abstract BigDecimal calcular(BigDecimal direita, BigDecimal esquerda);
}
