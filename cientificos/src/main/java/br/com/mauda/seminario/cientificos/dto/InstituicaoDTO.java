package br.com.mauda.seminario.cientificos.dto;

import br.com.mauda.seminario.cientificos.dto.util.ClassAttributeValidation;

public class InstituicaoDTO implements FilterValidation {
    private Long id;
    private String nome;
    private String sigla;
    private String cidade;
    private String estado;
    private String pais;

    @Override
    public boolean validateForFindData() {
        return ClassAttributeValidation.validateAllFields(this);
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
