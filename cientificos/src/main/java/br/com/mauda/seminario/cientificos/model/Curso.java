package br.com.mauda.seminario.cientificos.model;

public class Curso {

    private Long id;
    private String nome;
    private AreaCientifica areaCientifica;

    public Curso(AreaCientifica areaCientifica) {
        areaCientifica.getCursos().add(this);
        this.areaCientifica = areaCientifica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public AreaCientifica getAreaCientifica() {
        return areaCientifica;
    }

    public void setAreaCientifica(AreaCientifica areaCientifica) {
        this.areaCientifica = areaCientifica;
    }
}
