package com.carlos.CalculadoraRest.repository;

import com.carlos.CalculadoraRest.entity.ExpressaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ExpressaoRepository extends JpaRepository<ExpressaoEntity, Long> {

    @Query("SELECT exp FROM ExpressaoEntity exp WHERE exp.expressao = :expressao")
    Optional<ExpressaoEntity> getExpressaoPorDescricaoExpressao(String expressao);
}
