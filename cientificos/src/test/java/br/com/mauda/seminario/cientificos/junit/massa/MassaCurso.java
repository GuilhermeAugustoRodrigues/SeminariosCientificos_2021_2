package br.com.mauda.seminario.cientificos.junit.massa;

import static br.com.mauda.seminario.cientificos.junit.massa.MassaAreaCientifica.AC_LINGUAS;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaAreaCientifica.AC_MATEMATICA;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaAreaCientifica.AC_TECNOLOGIAS_GESTAO_INFORMACAO;

public enum MassaCurso {

    CURSO_ANALISE_SISTEMAS(1L, "Analise de Sistemas", AC_TECNOLOGIAS_GESTAO_INFORMACAO),
    CURSO_CIENCIA_COMPUTACAO(2L, "Ciencia da Computacao", AC_TECNOLOGIAS_GESTAO_INFORMACAO),
    CURSO_ENGENHARIA_COMPUTACAO(3L, "Engenharia da Computacao", AC_TECNOLOGIAS_GESTAO_INFORMACAO),

    CURSO_CALCULO(4L, "Calculo", AC_MATEMATICA),
    CURSO_DISCRETA(5L, "Matematica Discreta", AC_MATEMATICA),
    CURSO_FINANCEIRA(6L, "Matematica Financeira", AC_MATEMATICA),

    CURSO_INGLES(7L, "Ingles", AC_LINGUAS),
    CURSO_FRANCES(8L, "Frances", AC_LINGUAS),
    CURSO_PORTUGUES(9L, "Portugues", AC_LINGUAS);

    private Long id;
    private MassaAreaCientifica areaCientifica;
    private String nome;

    private MassaCurso(Long id, String nome, MassaAreaCientifica areaCientifica) {
        this.id = id;
        this.nome = nome;
        this.areaCientifica = areaCientifica;
    }

    public MassaAreaCientifica getAreaCientifica() {
        return this.areaCientifica;
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }
}