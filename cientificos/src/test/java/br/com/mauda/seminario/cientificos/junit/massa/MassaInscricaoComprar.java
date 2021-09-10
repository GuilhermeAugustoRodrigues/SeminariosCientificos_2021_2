package br.com.mauda.seminario.cientificos.junit.massa;

import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_01_INSCRICAO_01;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_01_INSCRICAO_02;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_01_INSCRICAO_03;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_01_INSCRICAO_04;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_01_INSCRICAO_05;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_01_INSCRICAO_06;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_02_INSCRICAO_01;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_02_INSCRICAO_02;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_02_INSCRICAO_03;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_02_INSCRICAO_04;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_02_INSCRICAO_05;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_02_INSCRICAO_06;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_03_INSCRICAO_01;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_03_INSCRICAO_02;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_03_INSCRICAO_03;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_03_INSCRICAO_04;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_03_INSCRICAO_05;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_03_INSCRICAO_06;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_04_INSCRICAO_01;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_04_INSCRICAO_02;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_04_INSCRICAO_03;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_04_INSCRICAO_04;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_04_INSCRICAO_05;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_04_INSCRICAO_06;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_05_INSCRICAO_01;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_05_INSCRICAO_02;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_05_INSCRICAO_03;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_05_INSCRICAO_04;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_05_INSCRICAO_05;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_05_INSCRICAO_06;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_06_INSCRICAO_01;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_06_INSCRICAO_02;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_06_INSCRICAO_03;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_06_INSCRICAO_04;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_06_INSCRICAO_05;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao.ESTUDANTE_06_INSCRICAO_06;

import br.com.mauda.seminario.cientificos.model.enums.SituacaoInscricaoEnum;

public enum MassaInscricaoComprar {

    COMPRAR_ESTUDANTE_01_INSCRICAO_01(ESTUDANTE_01_INSCRICAO_01),
    COMPRAR_ESTUDANTE_01_INSCRICAO_02(ESTUDANTE_01_INSCRICAO_02),
    COMPRAR_ESTUDANTE_01_INSCRICAO_03(ESTUDANTE_01_INSCRICAO_03),
    COMPRAR_ESTUDANTE_01_INSCRICAO_04(ESTUDANTE_01_INSCRICAO_04),
    COMPRAR_ESTUDANTE_01_INSCRICAO_05(ESTUDANTE_01_INSCRICAO_05),
    COMPRAR_ESTUDANTE_01_INSCRICAO_06(ESTUDANTE_01_INSCRICAO_06),

    COMPRAR_ESTUDANTE_02_INSCRICAO_01(ESTUDANTE_02_INSCRICAO_01),
    COMPRAR_ESTUDANTE_02_INSCRICAO_02(ESTUDANTE_02_INSCRICAO_02),
    COMPRAR_ESTUDANTE_02_INSCRICAO_03(ESTUDANTE_02_INSCRICAO_03),
    COMPRAR_ESTUDANTE_02_INSCRICAO_04(ESTUDANTE_02_INSCRICAO_04),
    COMPRAR_ESTUDANTE_02_INSCRICAO_05(ESTUDANTE_02_INSCRICAO_05),
    COMPRAR_ESTUDANTE_02_INSCRICAO_06(ESTUDANTE_02_INSCRICAO_06),

    COMPRAR_ESTUDANTE_03_INSCRICAO_01(ESTUDANTE_03_INSCRICAO_01),
    COMPRAR_ESTUDANTE_03_INSCRICAO_02(ESTUDANTE_03_INSCRICAO_02),
    COMPRAR_ESTUDANTE_03_INSCRICAO_03(ESTUDANTE_03_INSCRICAO_03),
    COMPRAR_ESTUDANTE_03_INSCRICAO_04(ESTUDANTE_03_INSCRICAO_04),
    COMPRAR_ESTUDANTE_03_INSCRICAO_05(ESTUDANTE_03_INSCRICAO_05),
    COMPRAR_ESTUDANTE_03_INSCRICAO_06(ESTUDANTE_03_INSCRICAO_06),

    COMPRAR_ESTUDANTE_04_INSCRICAO_01(ESTUDANTE_04_INSCRICAO_01),
    COMPRAR_ESTUDANTE_04_INSCRICAO_02(ESTUDANTE_04_INSCRICAO_02),
    COMPRAR_ESTUDANTE_04_INSCRICAO_03(ESTUDANTE_04_INSCRICAO_03),
    COMPRAR_ESTUDANTE_04_INSCRICAO_04(ESTUDANTE_04_INSCRICAO_04),
    COMPRAR_ESTUDANTE_04_INSCRICAO_05(ESTUDANTE_04_INSCRICAO_05),
    COMPRAR_ESTUDANTE_04_INSCRICAO_06(ESTUDANTE_04_INSCRICAO_06),

    COMPRAR_ESTUDANTE_05_INSCRICAO_01(ESTUDANTE_05_INSCRICAO_01),
    COMPRAR_ESTUDANTE_05_INSCRICAO_02(ESTUDANTE_05_INSCRICAO_02),
    COMPRAR_ESTUDANTE_05_INSCRICAO_03(ESTUDANTE_05_INSCRICAO_03),
    COMPRAR_ESTUDANTE_05_INSCRICAO_04(ESTUDANTE_05_INSCRICAO_04),
    COMPRAR_ESTUDANTE_05_INSCRICAO_05(ESTUDANTE_05_INSCRICAO_05),
    COMPRAR_ESTUDANTE_05_INSCRICAO_06(ESTUDANTE_05_INSCRICAO_06),

    COMPRAR_ESTUDANTE_06_INSCRICAO_01(ESTUDANTE_06_INSCRICAO_01),
    COMPRAR_ESTUDANTE_06_INSCRICAO_02(ESTUDANTE_06_INSCRICAO_02),
    COMPRAR_ESTUDANTE_06_INSCRICAO_03(ESTUDANTE_06_INSCRICAO_03),
    COMPRAR_ESTUDANTE_06_INSCRICAO_04(ESTUDANTE_06_INSCRICAO_04),
    COMPRAR_ESTUDANTE_06_INSCRICAO_05(ESTUDANTE_06_INSCRICAO_05),
    COMPRAR_ESTUDANTE_06_INSCRICAO_06(ESTUDANTE_06_INSCRICAO_06);

    private MassaInscricao inscricao;

    private MassaInscricaoComprar(MassaInscricao inscricao) {
        this.inscricao = inscricao;
    }

    public MassaInscricao getInscricao() {
        return this.inscricao;
    }

    public SituacaoInscricaoEnum getSituacaoAntes() {
        return SituacaoInscricaoEnum.DISPONIVEL;
    }

    public SituacaoInscricaoEnum getSituacaoApos() {
        return SituacaoInscricaoEnum.COMPRADO;
    }
}