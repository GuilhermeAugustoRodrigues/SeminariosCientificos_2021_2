package br.com.mauda.seminario.cientificos.junit.massa;

import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_01_INSCRICAO_01;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_01_INSCRICAO_02;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_01_INSCRICAO_03;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_02_INSCRICAO_01;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_02_INSCRICAO_02;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_02_INSCRICAO_03;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_03_INSCRICAO_01;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_03_INSCRICAO_02;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_03_INSCRICAO_03;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_04_INSCRICAO_01;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_04_INSCRICAO_02;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_04_INSCRICAO_03;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_05_INSCRICAO_01;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_05_INSCRICAO_02;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_05_INSCRICAO_03;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_06_INSCRICAO_01;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_06_INSCRICAO_02;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_06_INSCRICAO_03;

import br.com.mauda.seminario.cientificos.model.enums.SituacaoInscricaoEnum;

public enum MassaInscricaoCheckIn {

    CHECKIN_ESTUDANTE_01_INSCRICAO_01(ESTUDANTE_01_INSCRICAO_01),
    CHECKIN_ESTUDANTE_01_INSCRICAO_02(ESTUDANTE_01_INSCRICAO_02),
    CHECKIN_ESTUDANTE_01_INSCRICAO_03(ESTUDANTE_01_INSCRICAO_03),

    CHECKIN_ESTUDANTE_02_INSCRICAO_01(ESTUDANTE_02_INSCRICAO_01),
    CHECKIN_ESTUDANTE_02_INSCRICAO_02(ESTUDANTE_02_INSCRICAO_02),
    CHECKIN_ESTUDANTE_02_INSCRICAO_03(ESTUDANTE_02_INSCRICAO_03),

    CHECKIN_ESTUDANTE_03_INSCRICAO_01(ESTUDANTE_03_INSCRICAO_01),
    CHECKIN_ESTUDANTE_03_INSCRICAO_02(ESTUDANTE_03_INSCRICAO_02),
    CHECKIN_ESTUDANTE_03_INSCRICAO_03(ESTUDANTE_03_INSCRICAO_03),

    CHECKIN_ESTUDANTE_04_INSCRICAO_01(ESTUDANTE_04_INSCRICAO_01),
    CHECKIN_ESTUDANTE_04_INSCRICAO_02(ESTUDANTE_04_INSCRICAO_02),
    CHECKIN_ESTUDANTE_04_INSCRICAO_03(ESTUDANTE_04_INSCRICAO_03),

    CHECKIN_ESTUDANTE_05_INSCRICAO_01(ESTUDANTE_05_INSCRICAO_01),
    CHECKIN_ESTUDANTE_05_INSCRICAO_02(ESTUDANTE_05_INSCRICAO_02),
    CHECKIN_ESTUDANTE_05_INSCRICAO_03(ESTUDANTE_05_INSCRICAO_03),

    CHECKIN_ESTUDANTE_06_INSCRICAO_01(ESTUDANTE_06_INSCRICAO_01),
    CHECKIN_ESTUDANTE_06_INSCRICAO_02(ESTUDANTE_06_INSCRICAO_02),
    CHECKIN_ESTUDANTE_06_INSCRICAO_03(ESTUDANTE_06_INSCRICAO_03);

    private MassaInscricao inscricao;

    private MassaInscricaoCheckIn(MassaInscricao inscricao) {
        this.inscricao = inscricao;
    }

    public MassaInscricao getInscricao() {
        return this.inscricao;
    }

    public SituacaoInscricaoEnum getSituacaoAntes() {
        return SituacaoInscricaoEnum.COMPRADO;
    }

    public SituacaoInscricaoEnum getSituacaoApos() {
        return SituacaoInscricaoEnum.CHECKIN;
    }
}