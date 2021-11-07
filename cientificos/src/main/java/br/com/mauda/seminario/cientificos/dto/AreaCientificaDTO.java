package br.com.mauda.seminario.cientificos.dto;

import br.com.mauda.seminario.cientificos.dto.util.ClassAttributeValidation;

public class AreaCientificaDTO implements FilterValidation {
    private Long id;
    private String nome;

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
}
