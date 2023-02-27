package com.carlos.CalculadoraRest.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TB_EXP_EXPRESSAO")
public class ExpressaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXP_ID")
    private Long id;

    @Column(name = "EXP_DS_EXPRESSAO")
    private String expressao;

    @Column(name = "EXP_VL_RESPOSTA")
    private BigDecimal resultado;

    public ExpressaoEntity(String expressao, BigDecimal resultado) {
        this.expressao = expressao;
        this.resultado = resultado;
    }
}
