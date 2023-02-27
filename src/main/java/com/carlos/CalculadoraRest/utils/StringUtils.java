package com.carlos.CalculadoraRest.utils;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class StringUtils {

    private StringUtils() {}

    public static String removerEspacos(final String texto) {
        return texto.replaceAll("\\s+","");
    }

    public static List<String> obterTokensDaExpressao(final String expressao) {

        final List<String> tokens = Pattern.compile("(([\\d\\+][\\.][\\d\\*]|[\\d])|[/+]|[/*]|[//]|[/-])")
                .matcher(expressao)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.toList());

        return tokens;
    }

    public static boolean isNumero(String token) {
        return Pattern.compile("-?\\d+(\\.\\d+)?")
                .matcher(token)
                .matches();
    }

    public static boolean possuiOperadores(final String expressao) {
        return Pattern.compile("[\\*|\\/|\\+|\\-]")
                .matcher(expressao)
                .find();
    }
}
