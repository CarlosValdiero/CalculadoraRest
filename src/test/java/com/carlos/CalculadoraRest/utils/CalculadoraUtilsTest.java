package com.carlos.CalculadoraRest.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class CalculadoraUtilsTest {

    @Test
    void calcularNotacaoPosFixadaTest() {
        final List<String> expressaoPosFixada = List.of("2", "3", "3", "*", "+");

        final BigDecimal resultado = CalculadoraUtils.calcularNotacaoPosFixada(expressaoPosFixada);

        Assertions.assertEquals(BigDecimal.valueOf(11), resultado);
    }

    @Test
    void calcularTest() {
        final List<String> expressaoAlgebrica = List.of("2", "+", "3", "*", "3");

        final BigDecimal resultado = CalculadoraUtils.calcular(expressaoAlgebrica);

        Assertions.assertEquals(BigDecimal.valueOf(11), resultado);
    }
}