package com.carlos.CalculadoraRest.validator;

import com.carlos.CalculadoraRest.exception.CalculadoraException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraValidatorTest {

    @Test
    void validarCaracteresTest() {
        Assertions.assertDoesNotThrow(() -> CalculadoraValidator.validarCaracteres("2+3"));


        final CalculadoraException erroEncontrado = Assertions.assertThrows(CalculadoraException.class,
                () -> CalculadoraValidator.validarCaracteres("2?+3a"));
        final String mensagemErroEsperada = "Não esperado '?' na posição 2. Não esperado 'a' na posição 5";
        Assertions.assertEquals(mensagemErroEsperada, erroEncontrado.getMessage());
    }

    @Test
    void validarSintaxeTest() {
        Assertions.assertDoesNotThrow(() -> CalculadoraValidator.validarSintaxe("2+3"));
        Assertions.assertDoesNotThrow(() -> CalculadoraValidator.validarSintaxe("2.1*3"));
        Assertions.assertDoesNotThrow(() -> CalculadoraValidator.validarSintaxe("2.1+3-1*2/1"));
        Assertions.assertThrows(CalculadoraException.class,
                () -> CalculadoraValidator.validarSintaxe("2.1+-1*2/1"));

    }
}