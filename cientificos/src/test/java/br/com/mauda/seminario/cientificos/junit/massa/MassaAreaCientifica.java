package br.com.mauda.seminario.cientificos.junit.massa;

public enum MassaAreaCientifica {

    AC_TECNOLOGIAS_GESTAO_INFORMACAO(1L, "Tecnologias de Gestao da Informacao"),
    AC_MATEMATICA(2L, "Matematica"),
    AC_LINGUAS(3L, "Linguas");

    private Long id;
    private String nome;

    private MassaAreaCientifica(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }
}