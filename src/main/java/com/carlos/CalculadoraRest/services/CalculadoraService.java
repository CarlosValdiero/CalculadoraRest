package com.carlos.CalculadoraRest.services;

import com.carlos.CalculadoraRest.dto.ResultadoExpressaoResponseDTO;
import com.carlos.CalculadoraRest.entities.ExpressaoEntity;
import com.carlos.CalculadoraRest.repository.ExpressaoRepository;
import com.carlos.CalculadoraRest.utils.CalculadoraUtils;
import com.carlos.CalculadoraRest.utils.StringUtils;
import com.carlos.CalculadoraRest.validator.CalculadoraValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class CalculadoraService {

    private final ExpressaoRepository expressaoRepository;

    public CalculadoraService(ExpressaoRepository expressaoRepository) {
        this.expressaoRepository = expressaoRepository;
    }

    public ResultadoExpressaoResponseDTO calcular(final String expressao) {
        final String expressaoSemEspacos = StringUtils.removerEspacos(expressao);

        CalculadoraValidator.validarExpressao(expressaoSemEspacos);

        Optional<ExpressaoEntity> expressaoOptional = expressaoRepository.getExpressaoPorDescricaoExpressao(expressao);

        if(expressaoOptional.isPresent()) {
            final BigDecimal resultadoRecuperado = expressaoOptional.get().getResultado();
            log.debug("Resultado da expressão recuperado, resultado: {}.", resultadoRecuperado);
            return new ResultadoExpressaoResponseDTO(resultadoRecuperado);
        }

        final List<String> expressaoAlgebrica = StringUtils.obterTokensDaExpressao(expressaoSemEspacos);

        final BigDecimal resultado = CalculadoraUtils.calcular(expressaoAlgebrica);

        salvarExpressaoCalculada(expressaoSemEspacos, resultado);

        return new ResultadoExpressaoResponseDTO(resultado);
    }

    private void salvarExpressaoCalculada(String expressaoSemEspacos, BigDecimal resultado) {
        final ExpressaoEntity expressaoEntity = new ExpressaoEntity(expressaoSemEspacos, resultado);
        this.expressaoRepository.save(expressaoEntity);
        log.debug("Expressão salva.");
    }
}
