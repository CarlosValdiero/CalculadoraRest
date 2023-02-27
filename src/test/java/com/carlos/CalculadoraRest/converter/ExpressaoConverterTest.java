package com.carlos.CalculadoraRest.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ExpressaoConverterTest {

    @Test
    void notacaoAlgebricaParaPosFixadaTest() {
        final List<String> expressao = List.of("2", "+", "3", "*", "3");
        final List<String> expressaoPolonesaInversa = ExpressaoConverter.notacaoAlgebricaParaPosFixada(expressao);

        Assertions.assertEquals(5, expressaoPolonesaInversa.size());
        Assertions.assertEquals("2", expressaoPolonesaInversa.get(0));
        Assertions.assertEquals("3", expressaoPolonesaInversa.get(1));
        Assertions.assertEquals("3", expressaoPolonesaInversa.get(2));
        Assertions.assertEquals("*", expressaoPolonesaInversa.get(3));
        Assertions.assertEquals("+", expressaoPolonesaInversa.get(4));
    }
}