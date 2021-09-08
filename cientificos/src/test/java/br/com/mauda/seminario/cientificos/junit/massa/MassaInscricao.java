package br.com.mauda.seminario.cientificos.junit.massa;

import static br.com.mauda.seminario.cientificos.junit.massa.MassaEstudante.ESTUDANTE_01;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaEstudante.ESTUDANTE_02;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaEstudante.ESTUDANTE_03;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaEstudante.ESTUDANTE_04;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaEstudante.ESTUDANTE_05;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaEstudante.ESTUDANTE_06;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaSeminario.SEMINARIO_INGLES;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaSeminario.SEMINARIO_JAVA;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaSeminario.SEMINARIO_LINGUAS;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaSeminario.SEMINARIO_MATEMATICA;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaSeminario.SEMINARIO_MATEMATICA_DISCRETA;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaSeminario.SEMINARIO_TECNOLOGIAS_GESTAO_INFORMACAO;

import br.com.mauda.seminario.cientificos.model.enums.SituacaoInscricaoEnum;

public enum MassaInscricao {

    ESTUDANTE_01_INSCRICAO_01(ESTUDANTE_01, SEMINARIO_TECNOLOGIAS_GESTAO_INFORMACAO, true),
    ESTUDANTE_01_INSCRICAO_02(ESTUDANTE_01, SEMINARIO_JAVA, true),
    ESTUDANTE_01_INSCRICAO_03(ESTUDANTE_01, SEMINARIO_MATEMATICA_DISCRETA, true),
    ESTUDANTE_01_INSCRICAO_04(ESTUDANTE_01, SEMINARIO_MATEMATICA, true),
    ESTUDANTE_01_INSCRICAO_05(ESTUDANTE_01, SEMINARIO_LINGUAS, true),
    ESTUDANTE_01_INSCRICAO_06(ESTUDANTE_01, SEMINARIO_INGLES, true),

    ESTUDANTE_02_INSCRICAO_01(ESTUDANTE_02, SEMINARIO_TECNOLOGIAS_GESTAO_INFORMACAO, false),
    ESTUDANTE_02_INSCRICAO_02(ESTUDANTE_02, SEMINARIO_JAVA, false),
    ESTUDANTE_02_INSCRICAO_03(ESTUDANTE_02, SEMINARIO_MATEMATICA_DISCRETA, false),
    ESTUDANTE_02_INSCRICAO_04(ESTUDANTE_02, SEMINARIO_MATEMATICA, false),
    ESTUDANTE_02_INSCRICAO_05(ESTUDANTE_02, SEMINARIO_LINGUAS, false),
    ESTUDANTE_02_INSCRICAO_06(ESTUDANTE_02, SEMINARIO_INGLES, false),

    ESTUDANTE_03_INSCRICAO_01(ESTUDANTE_03, SEMINARIO_TECNOLOGIAS_GESTAO_INFORMACAO, true),
    ESTUDANTE_03_INSCRICAO_02(ESTUDANTE_03, SEMINARIO_JAVA, false),
    ESTUDANTE_03_INSCRICAO_03(ESTUDANTE_03, SEMINARIO_MATEMATICA_DISCRETA, true),
    ESTUDANTE_03_INSCRICAO_04(ESTUDANTE_03, SEMINARIO_MATEMATICA, false),
    ESTUDANTE_03_INSCRICAO_05(ESTUDANTE_03, SEMINARIO_LINGUAS, true),
    ESTUDANTE_03_INSCRICAO_06(ESTUDANTE_03, SEMINARIO_INGLES, false),

    ESTUDANTE_04_INSCRICAO_01(ESTUDANTE_04, SEMINARIO_TECNOLOGIAS_GESTAO_INFORMACAO, false),
    ESTUDANTE_04_INSCRICAO_02(ESTUDANTE_04, SEMINARIO_JAVA, true),
    ESTUDANTE_04_INSCRICAO_03(ESTUDANTE_04, SEMINARIO_MATEMATICA_DISCRETA, false),
    ESTUDANTE_04_INSCRICAO_04(ESTUDANTE_04, SEMINARIO_MATEMATICA, true),
    ESTUDANTE_04_INSCRICAO_05(ESTUDANTE_04, SEMINARIO_LINGUAS, false),
    ESTUDANTE_04_INSCRICAO_06(ESTUDANTE_04, SEMINARIO_INGLES, true),

    ESTUDANTE_05_INSCRICAO_01(ESTUDANTE_05, SEMINARIO_TECNOLOGIAS_GESTAO_INFORMACAO, true),
    ESTUDANTE_05_INSCRICAO_02(ESTUDANTE_05, SEMINARIO_JAVA, false),
    ESTUDANTE_05_INSCRICAO_03(ESTUDANTE_05, SEMINARIO_MATEMATICA_DISCRETA, false),
    ESTUDANTE_05_INSCRICAO_04(ESTUDANTE_05, SEMINARIO_MATEMATICA, false),
    ESTUDANTE_05_INSCRICAO_05(ESTUDANTE_05, SEMINARIO_LINGUAS, false),
    ESTUDANTE_05_INSCRICAO_06(ESTUDANTE_05, SEMINARIO_INGLES, false),

    ESTUDANTE_06_INSCRICAO_01(ESTUDANTE_06, SEMINARIO_TECNOLOGIAS_GESTAO_INFORMACAO, false),
    ESTUDANTE_06_INSCRICAO_02(ESTUDANTE_06, SEMINARIO_JAVA, true),
    ESTUDANTE_06_INSCRICAO_03(ESTUDANTE_06, SEMINARIO_MATEMATICA_DISCRETA, true),
    ESTUDANTE_06_INSCRICAO_04(ESTUDANTE_06, SEMINARIO_MATEMATICA, true),
    ESTUDANTE_06_INSCRICAO_05(ESTUDANTE_06, SEMINARIO_LINGUAS, true),
    ESTUDANTE_06_INSCRICAO_06(ESTUDANTE_06, SEMINARIO_INGLES, true);

    private MassaEstudante estudante;
    private MassaSeminario seminario;

    private boolean direitoMaterial;

    private MassaInscricao(MassaEstudante estudante, MassaSeminario seminario, boolean direitoMaterial) {
        this.estudante = estudante;
        this.seminario = seminario;
        this.direitoMaterial = direitoMaterial;
    }

    public MassaEstudante getEstudante() {
        return this.estudante;
    }

    public MassaSeminario getSeminario() {
        return this.seminario;
    }

    public boolean isDireitoMaterial() {
        return this.direitoMaterial;
    }

    public SituacaoInscricaoEnum getSituacao() {
        return SituacaoInscricaoEnum.DISPONIVEL;
    }
}