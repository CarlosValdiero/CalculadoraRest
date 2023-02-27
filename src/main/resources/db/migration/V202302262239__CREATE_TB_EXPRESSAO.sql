-- Criação da tabela para persistir o resultado das expressões já calculadas.

CREATE TABLE TB_EXP_EXPRESSAO
(
    EXP_ID bigint AUTO_INCREMENT,
    EXP_DS_EXPRESSAO character varying(1048576) NOT NULL,
    EXP_VL_RESPOSTA numeric(19, 2) NOT NULL,
    PRIMARY KEY (EXP_ID)
);

CREATE INDEX INDEX_EXP_DS_EXPRESSAO ON TB_EXP_EXPRESSAO(EXP_DS_EXPRESSAO);