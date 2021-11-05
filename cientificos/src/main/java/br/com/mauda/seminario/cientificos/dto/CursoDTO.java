package br.com.mauda.seminario.cientificos.dto;

import br.com.mauda.seminario.cientificos.dto.util.ClassAttributeValidation;

public class CursoDTO implements FilterValidation {
    private Long id;
    private String nome;
    private Long idAreaCientifica;
    private String nomeAreaCientifica;

    @Override
    public boolean validateForFindData() {
        return ClassAttributeValidation.hasNonNullAttribute(this);
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

    public Long getIdAreaCientifica() {
        return idAreaCientifica;
    }

    public void setIdAreaCientifica(Long idAreaCientifica) {
        this.idAreaCientifica = idAreaCientifica;
    }

    public String getNomeAreaCientifica() {
        return nomeAreaCientifica;
    }

    public void setNomeAreaCientifica(String nomeAreaCientifica) {
        this.nomeAreaCientifica = nomeAreaCientifica;
    }
}
