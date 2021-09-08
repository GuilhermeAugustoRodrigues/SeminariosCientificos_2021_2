package br.com.mauda.seminario.cientificos.junit.massa;

import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_01_INSCRICAO_06;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_02_INSCRICAO_06;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_03_INSCRICAO_06;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_04_INSCRICAO_06;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_05_INSCRICAO_06;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_06_INSCRICAO_06;

import br.com.mauda.seminario.cientificos.model.enums.SituacaoInscricaoEnum;

public enum MassaInscricaoCancelarCompra {

    CANCELAR_ESTUDANTE_01_INSCRICAO_06(ESTUDANTE_01_INSCRICAO_06),

    CANCELAR_ESTUDANTE_02_INSCRICAO_06(ESTUDANTE_02_INSCRICAO_06),

    CANCELAR_ESTUDANTE_03_INSCRICAO_06(ESTUDANTE_03_INSCRICAO_06),

    CANCELAR_ESTUDANTE_04_INSCRICAO_06(ESTUDANTE_04_INSCRICAO_06),

    CANCELAR_ESTUDANTE_05_INSCRICAO_06(ESTUDANTE_05_INSCRICAO_06),

    CANCELAR_ESTUDANTE_06_INSCRICAO_06(ESTUDANTE_06_INSCRICAO_06);

    private MassaInscricao inscricao;

    private MassaInscricaoCancelarCompra(MassaInscricao inscricao) {
        this.inscricao = inscricao;
    }

    public MassaInscricao getInscricao() {
        return this.inscricao;
    }

    public SituacaoInscricaoEnum getSituacaoAntes() {
        return SituacaoInscricaoEnum.COMPRADO;
    }

    public SituacaoInscricaoEnum getSituacaoApos() {
        return SituacaoInscricaoEnum.DISPONIVEL;
    }
}