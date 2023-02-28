package com.carlos.CalculadoraRest.validator;

import com.carlos.CalculadoraRest.exception.CalculadoraException;
import com.carlos.CalculadoraRest.utils.StringUtils;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class CalculadoraValidator {

    private CalculadoraValidator() {}

    public static void validarExpressao(final String expressao) {
        validarCaracteres(expressao);
        validarSintaxe(expressao);
    }

    public static void validarCaracteres(final String expressao) {

        final List<String> matches = Pattern.compile("[^\\d|\\.|\\*|\\/|\\+|\\-]")
                .matcher(expressao)
                .results()
                .map(matchResult -> "Não esperado '"+matchResult.group()+"' na posição "+ (matchResult.start()+1))
                .collect(Collectors.toList());

        if(!matches.isEmpty()) {
            throw new CalculadoraException(String.join(". ", matches));
        }
    }

    public static void validarSintaxe(final String expressao) {

        if(!isNumero(expressao.charAt(0))) {
            throw new CalculadoraException("A expressão deve iniciar com um número!");
        }

        if(!isNumero(expressao.charAt(expressao.length()-1))) {
            throw new CalculadoraException("A expressão deve terminar com um número!");
        }

        if(!StringUtils.possuiOperadores(expressao)) {
            throw new CalculadoraException("A expressão deve conter pelo menos um operador! operadores: '+', '-', '*', '/'.");
        }

        final List<String> matches = Pattern.compile("[\\*|\\/|\\+|\\-][\\*|\\/|\\+|\\-]")
                .matcher(expressao)
                .results()
                .map(matchResult -> "Os operadores devem estar entre números, Não esperado '"+matchResult.group()+"' na posição "+ (matchResult.start()+1))
                .collect(Collectors.toList());

        if(!matches.isEmpty()) {
            throw new CalculadoraException(String.join(". ", matches));
        }
    }

    private static boolean isNumero(final char caracter) {
        return StringUtils.isNumero(String.valueOf(caracter));
    }
}
