package com.carlos.CalculadoraRest.dto;

import lombok.Getter;

import java.math.BigDecimal;


@Getter
public class ResultadoExpressaoResponseDTO {
    private final String resultado;

    public ResultadoExpressaoResponseDTO(BigDecimal resultado) {
        this.resultado = resultado.toPlainString();
    }
}
