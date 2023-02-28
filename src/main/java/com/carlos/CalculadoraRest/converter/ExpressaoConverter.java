package com.carlos.CalculadoraRest.converter;

import com.carlos.CalculadoraRest.enumeration.OperadorEnum;
import com.carlos.CalculadoraRest.utils.StringUtils;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public final class ExpressaoConverter {

    private ExpressaoConverter() {
    }

    public static List<String> notacaoAlgebricaParaPosFixada(final List<String> expressaoAlgebrica) {
        Deque<String> pilhaOperadores = new LinkedList<>();
        List<String> expressaoPosFixada = new ArrayList<>();

        expressaoAlgebrica.forEach(token -> {
            if (StringUtils.isNumero(token)) {
                expressaoPosFixada.add(token);
            } else {
                while (!pilhaOperadores.isEmpty() && isMaiorPrecedencia(token, pilhaOperadores.peek())) {
                    expressaoPosFixada.add(pilhaOperadores.pop());
                }
                pilhaOperadores.push(token);
            }
        });

        while (!pilhaOperadores.isEmpty())
            expressaoPosFixada.add(pilhaOperadores.pop());

        return expressaoPosFixada;
    }

    private static boolean isMaiorPrecedencia(String operadorAtual, String operadorAnterior) {
        return (getPrecedencia(operadorAnterior) >= getPrecedencia(operadorAtual));
    }

    private static int getPrecedencia(String operador) {
        if (isOperador(operador, OperadorEnum.SOMA) || isOperador(operador, OperadorEnum.SUBTRACAO)) {
            return 1;
        } else if (isOperador(operador, OperadorEnum.MULTIPLICACAO) || isOperador(operador, OperadorEnum.DIVISAO)) {
            return 2;
        } else {
            return -1;
        }
    }

    private static boolean isOperador(String operador, OperadorEnum operadorEnum) {
        return operador.equals(operadorEnum.getCodigo());
    }
}
