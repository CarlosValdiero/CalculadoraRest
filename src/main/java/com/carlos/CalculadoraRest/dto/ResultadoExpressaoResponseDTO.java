package com.carlos.CalculadoraRest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Getter
@Setter
@NoArgsConstructor
public class ResultadoExpressaoResponseDTO {
    private String resultado;

    public ResultadoExpressaoResponseDTO(BigDecimal resultado) {
        if(resultado.scale() > 2) {
            resultado = resultado.setScale(2, RoundingMode.UP);
        }
        this.resultado = resultado.toPlainString();
    }
}
