package br.com.mauda.seminario.cientificos.junit.massa;

import static br.com.mauda.seminario.cientificos.junit.massa.MassaAreaCientifica.AC_LINGUAS;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaAreaCientifica.AC_MATEMATICA;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaAreaCientifica.AC_TECNOLOGIAS_GESTAO_INFORMACAO;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaProfessor.PROFESSOR_01;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaProfessor.PROFESSOR_02;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaProfessor.PROFESSOR_03;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaProfessor.PROFESSOR_04;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaProfessor.PROFESSOR_05;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaProfessor.PROFESSOR_06;

import java.time.LocalDate;

public enum MassaSeminario {

    SEMINARIO_TECNOLOGIAS_GESTAO_INFORMACAO(
        1L,
        "Seminario de Tecnologias de Gestao da Informacao",
        LocalDate.now().plusDays(1L),
        "Seminario Especializado em Tecnologias de Gestao da Informacao",
        false,
        10,
        PROFESSOR_01,
        AC_TECNOLOGIAS_GESTAO_INFORMACAO),

    SEMINARIO_JAVA(
        2L,
        "Seminario de Java",
        LocalDate.now().plusDays(2L),
        "Seminario Especializado na Linguagem Java",
        false,
        10,
        PROFESSOR_02,
        AC_TECNOLOGIAS_GESTAO_INFORMACAO),

    SEMINARIO_MATEMATICA_DISCRETA(
        3L,
        "Seminario de Discreta, a Matematica",
        LocalDate.now().plusDays(3L),
        "Seminario de Matematica Discreta",
        true,
        15,
        PROFESSOR_03,
        AC_MATEMATICA),

    SEMINARIO_MATEMATICA(
        4L,
        "Seminario de Matematica",
        LocalDate.now().plusDays(4L),
        "Seminario Especializado em Matematica",
        true,
        15,
        PROFESSOR_04,
        AC_MATEMATICA),

    SEMINARIO_LINGUAS(
        5L,
        "Seminario de Linguas",
        LocalDate.now().plusDays(5L),
        "Seminario Especializado em Linguas",
        true,
        15,
        PROFESSOR_05,
        AC_LINGUAS),

    SEMINARIO_INGLES(
        6L,
        "Seminario de Lingua Inglesa",
        LocalDate.now().plusDays(6L),
        "Seminario Especializado em Lingua Inglesa",
        true,
        15,
        PROFESSOR_06,
        AC_LINGUAS);

    private Long id;
    private MassaAreaCientifica areaCientifica;
    private LocalDate data;
    private String descricao;
    private Boolean mesaRedonda;
    private MassaProfessor professor;
    private int qtdInscricoes;
    private String titulo;

    private MassaSeminario(Long id, String titulo, LocalDate data, String descricao, Boolean mesaRedonda, int qtdInscricoes, MassaProfessor professor,
        MassaAreaCientifica areaCientifica) {

        this.id = id;
        this.areaCientifica = areaCientifica;
        this.data = data;
        this.descricao = descricao;
        this.mesaRedonda = mesaRedonda;
        this.professor = professor;
        this.qtdInscricoes = qtdInscricoes;
        this.titulo = titulo;
    }

    public Long getId() {
        return this.id;
    }

    public MassaAreaCientifica getAreaCientifica() {
        return this.areaCientifica;
    }

    public LocalDate getData() {
        return this.data;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public Boolean getMesaRedonda() {
        return this.mesaRedonda;
    }

    public MassaProfessor getProfessor() {
        return this.professor;
    }

    public Integer getQtdInscricoes() {
        return this.qtdInscricoes;
    }

    public String getTitulo() {
        return this.titulo;
    }
}