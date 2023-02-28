package com.carlos.CalculadoraRest.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class StringUtilsTest {

    @Test
    void removerEspacosTest() {
        Assertions.assertEquals("abc", StringUtils.removerEspacos(" a  b c "));
    }

    @Test
    void obterTokensDaExpressaoTest() {
        final String expressao = "121.22*4+2";

        final List<String> tokens = StringUtils.obterTokensDaExpressao(expressao);

        Assertions.assertEquals(5, tokens.size());
        Assertions.assertEquals("121.22", tokens.get(0));
        Assertions.assertEquals("*", tokens.get(1));
        Assertions.assertEquals("4", tokens.get(2));
        Assertions.assertEquals("+", tokens.get(3));
        Assertions.assertEquals("2", tokens.get(4));
    }

    @Test
    void possuiOperadoresTest() {
        Assertions.assertTrue(StringUtils.possuiOperadores("2+3"));
        Assertions.assertFalse(StringUtils.possuiOperadores("23"));
    }
}