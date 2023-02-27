package com.carlos.CalculadoraRest.utils;

import com.carlos.CalculadoraRest.enumeration.OperadorEnum;
import com.carlos.CalculadoraRest.converter.ExpressaoConverter;

import java.math.BigDecimal;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public final class CalculadoraUtils {

    private CalculadoraUtils() {}

    public static BigDecimal calcular(final List<String> expressao) {
        final List<String> expressaoPosFixada = ExpressaoConverter.notacaoAlgebricaParaPosFixada(expressao);

        return calcularNotacaoPosFixada(expressaoPosFixada);
    }

    public static BigDecimal calcularNotacaoPosFixada(final List<String> expressaoPosFixada) {
        Deque<BigDecimal> pilhaNumeros = new LinkedList<>();

        expressaoPosFixada.forEach(token -> {
            if(StringUtils.isNumero(token)) {
                pilhaNumeros.push(new BigDecimal(token));
            } else {
                final BigDecimal valorResultante = OperadorEnum.getPorCodigo(token)
                        .calcular(pilhaNumeros.pop(), pilhaNumeros.pop());
                pilhaNumeros.push(valorResultante);
            }
        });

        return pilhaNumeros.pop();
    }
}
