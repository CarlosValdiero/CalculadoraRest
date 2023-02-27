package com.carlos.CalculadoraRest.converter;

import com.carlos.CalculadoraRest.utils.StringUtils;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public final class ExpressaoConverter {

    private ExpressaoConverter() {}

    public static List<String> notacaoAlgebricaParaPosFixada(final List<String> expressaoAlgebrica) {
        Deque<String> pilhaOperadores = new LinkedList<>();
        List<String> expressaoPosFixada = new ArrayList<>();

        expressaoAlgebrica.forEach(token -> {
            if(StringUtils.isNumero(token)) {
                expressaoPosFixada.add(token);
            } else {
                while ( !pilhaOperadores.isEmpty() && isMaiorPrecedencia(token, pilhaOperadores.peek())) {
                    expressaoPosFixada.add(pilhaOperadores.pop());
                }
                pilhaOperadores.push(token);
            }
        });

        while ( ! pilhaOperadores.isEmpty())
            expressaoPosFixada.add(pilhaOperadores.pop());

        return expressaoPosFixada;
    }
    private static boolean isMaiorPrecedencia(String op, String sub)
    {
        return (getPrecedencia(sub) >= getPrecedencia(op));
    }

    private static int getPrecedencia(String operador) {
        if (operador.equals("+") || operador.equals("-")) {
            return 1;
        } else if (operador.equals("*") || operador.equals("/")) {
            return 2;
        } else {
            return -1;
        }
    }
}
