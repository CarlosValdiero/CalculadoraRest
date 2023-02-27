package com.carlos.CalculadoraRest.api;

import com.carlos.CalculadoraRest.dto.ExpressaoRequestDTO;
import com.carlos.CalculadoraRest.dto.ResultadoExpressaoResponseDTO;
import com.carlos.CalculadoraRest.services.CalculadoraService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(value = "expressao")
public class CalculadoraController {

    private final CalculadoraService calculadoraService;

    public CalculadoraController(CalculadoraService calculadoraService) {
        this.calculadoraService = calculadoraService;
    }

    @PostMapping("calcular")
    public ResponseEntity<ResultadoExpressaoResponseDTO> calcular(@RequestBody ExpressaoRequestDTO requestDTO) {
        log.info("GET /expressao/calcular, Expressao: {}.", requestDTO.getExpressao());
        final ResultadoExpressaoResponseDTO resultado = this.calculadoraService.calcular(requestDTO.getExpressao());
        log.info("Finalizado GET /expressao/calcular, Expressao: {}.", requestDTO.getExpressao());
        return ResponseEntity.ok(resultado);
    }
}
