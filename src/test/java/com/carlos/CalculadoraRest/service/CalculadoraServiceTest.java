package com.carlos.CalculadoraRest.service;

import com.carlos.CalculadoraRest.dto.ResultadoExpressaoResponseDTO;
import com.carlos.CalculadoraRest.entity.ExpressaoEntity;
import com.carlos.CalculadoraRest.exception.CalculadoraException;
import com.carlos.CalculadoraRest.repository.ExpressaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CalculadoraServiceTest {

    @Mock
    private ExpressaoRepository expressaoRepository;

    @InjectMocks
    private CalculadoraService calculadoraService;

    @Test
    void calcularTest() {
        final String expressao = "2+3*3";

        Mockito.when(this.expressaoRepository.getExpressaoPorDescricaoExpressao(expressao))
                .thenReturn(Optional.empty());

        final ResultadoExpressaoResponseDTO resultado = this.calculadoraService.calcular(expressao);

        Assertions.assertEquals("11", resultado.getResultado());

        Mockito.verify(this.expressaoRepository, Mockito.times(1))
                .save(Mockito.argThat(expressaoSalva -> expressaoSalva.getExpressao().equals(expressao)
                        && expressaoSalva.getResultado().compareTo(new BigDecimal(11)) == 0));
    }

    @Test
    void calcularDivisaoTest() {
        final String expressao = "2.33/3";

        Mockito.when(this.expressaoRepository.getExpressaoPorDescricaoExpressao(expressao))
                .thenReturn(Optional.empty());

        final ResultadoExpressaoResponseDTO resultado = this.calculadoraService.calcular(expressao);

        Assertions.assertEquals("0.78", resultado.getResultado());

        Mockito.verify(this.expressaoRepository, Mockito.times(1))
                .save(Mockito.argThat(expressaoSalva -> expressaoSalva.getExpressao().equals(expressao)
                        && expressaoSalva.getResultado().compareTo(new BigDecimal("0.78")) == 0));
    }

    @Test
    void calcularQuandoExpressaoJaCalculadaTest() {
        final String expressao = "2.33/3";

        final ExpressaoEntity expressaoEntity = new ExpressaoEntity(expressao, new BigDecimal("0.78"));
        Mockito.when(this.expressaoRepository.getExpressaoPorDescricaoExpressao(expressao))
                .thenReturn(Optional.of(expressaoEntity));

        final ResultadoExpressaoResponseDTO resultado = this.calculadoraService.calcular(expressao);

        Assertions.assertEquals("0.78", resultado.getResultado());

        Mockito.verify(this.expressaoRepository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    void calcularErroExpressaoInvalidaTest() {
        final String expressao = "+3*3";

        final CalculadoraException erro = Assertions.assertThrows(CalculadoraException.class,
                () -> this.calculadoraService.calcular(expressao));

        final String mensagemErro = "A expressão deve iniciar com um número!";

        Assertions.assertEquals(mensagemErro, erro.getMessage());

        Mockito.verify(this.expressaoRepository, Mockito.times(0))
                .getExpressaoPorDescricaoExpressao(Mockito.any());
        Mockito.verify(this.expressaoRepository, Mockito.times(0))
                .save(Mockito.any());
    }
}