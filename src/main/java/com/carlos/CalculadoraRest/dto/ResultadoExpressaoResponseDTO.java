package com.carlos.CalculadoraRest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
public class ResultadoExpressaoResponseDTO {
    private String resultado;

    public ResultadoExpressaoResponseDTO(BigDecimal resultado) {
        this.resultado = resultado.toPlainString();
    }
}
