package br.com.mauda.seminario.cientificos.junit.massa;

public enum MassaInstituicao {

    UEL(1L, "UEL", "Universidade Estadual de Londrina", "Londrina", "PR", "Brasil"),
    UEM(2L, "UEM", "Universidade Estadual de Maringa", "Maringa", "PR", "Brasil"),
    UFPR(3L, "UFPR", "Universidade Federal do Parana", "Curitiba", "PR", "Brasil"),
    UEPG(4L, "UEPG", "Universidade Estadual de Ponta Grossa", "Ponta Grossa", "PR", "Brasil"),
    UTFPR_PATO_BRANCO(5L, "UTFPR-PB", "Universidade Tecnologica Federal do Parana de Pato Branco", "Pato Branco", "PR", "Brasil"),
    UTFPR_TOLEDO(6L, "UTFPR-T", "Universidade Tecnologica Federal do Parana de Toledo", "Toledo", "PR", "Brasil");

    private Long id;
    private String cidade;
    private String estado;
    private String nome;
    private String pais;
    private String sigla;

    private MassaInstituicao(Long id, String sigla, String nome, String cidade, String estado, String pais) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    public Long getId() {
        return this.id;
    }

    public String getCidade() {
        return this.cidade;
    }

    public String getEstado() {
        return this.estado;
    }

    public String getNome() {
        return this.nome;
    }

    public String getPais() {
        return this.pais;
    }

    public String getSigla() {
        return this.sigla;
    }
}