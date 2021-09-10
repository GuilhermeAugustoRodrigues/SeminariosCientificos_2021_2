package br.com.mauda.seminario.cientificos.junit.massa;

import static br.com.mauda.seminario.cientificos.junit.massa.MassaInstituicao.UEL;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInstituicao.UEM;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInstituicao.UEPG;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInstituicao.UFPR;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInstituicao.UTFPR_PATO_BRANCO;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInstituicao.UTFPR_TOLEDO;

public enum MassaEstudante {

    ESTUDANTE_01(1L, "Estudante 01", "estudante01@estudantes.com.br", "f11111111", UEL),
    ESTUDANTE_02(2L, "Estudante 02", "estudante02@estudantes.com.br", "f22222222", UEM),
    ESTUDANTE_03(3L, "Estudante 03", "estudante03@estudantes.com.br", "f33333333", UEPG),
    ESTUDANTE_04(4L, "Estudante 04", "estudante04@estudantes.com.br", "f44444444", UFPR),
    ESTUDANTE_05(5L, "Estudante 05", "estudante05@estudantes.com.br", "f55555555", UTFPR_PATO_BRANCO),
    ESTUDANTE_06(6L, "Estudante 06", "estudante06@estudantes.com.br", "f66666666", UTFPR_TOLEDO);

    private Long id;
    private String email;
    private MassaInstituicao instituicao;
    private String nome;
    private String telefone;

    private MassaEstudante(Long id, String nome, String email, String telefone, MassaInstituicao instituicao) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.telefone = telefone;
        this.instituicao = instituicao;
    }

    public Long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public MassaInstituicao getInstituicao() {
        return this.instituicao;
    }

    public String getNome() {
        return this.nome;
    }

    public String getTelefone() {
        return this.telefone;
    }
}