package br.com.mauda.seminario.cientificos.junit.massa;

import static br.com.mauda.seminario.cientificos.junit.massa.MassaInstituicao.UEL;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInstituicao.UEM;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInstituicao.UEPG;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInstituicao.UFPR;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInstituicao.UTFPR_PATO_BRANCO;
import static br.com.mauda.seminario.cientificos.junit.massa.MassaInstituicao.UTFPR_TOLEDO;

public enum MassaProfessor {

    PROFESSOR_01(1L, "Professor 01", "professor01@professores.com.br", "f11111111", 2600.00, UEL),
    PROFESSOR_02(2L, "Professor 02", "professor02@professores.com.br", "f22222222", 3000.00, UEM),
    PROFESSOR_03(3L, "Professor 03", "professor03@professores.com.br", "f33333333", 3500.00, UEPG),
    PROFESSOR_04(4L, "Professor 04", "professor04@professores.com.br", "f44444444", 4000.00, UFPR),
    PROFESSOR_05(5L, "Professor 05", "professor05@professores.com.br", "f55555555", 4500.00, UTFPR_PATO_BRANCO),
    PROFESSOR_06(6L, "Professor 06", "professor06@professores.com.br", "f66666666", 4550.00, UTFPR_TOLEDO);

    private Long id;
    private String email;
    private MassaInstituicao instituicao;
    private String nome;
    private Double salario;
    private String telefone;

    private MassaProfessor(Long id, String nome, String email, String telefone, Double salario, MassaInstituicao instituicao) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.telefone = telefone;
        this.salario = salario;
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

    public Double getSalario() {
        return this.salario;
    }

    public String getTelefone() {
        return this.telefone;
    }
}