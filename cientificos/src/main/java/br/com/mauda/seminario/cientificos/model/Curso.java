package br.com.mauda.seminario.cientificos.model;

import br.com.mauda.seminario.cientificos.exception.ObjetoNuloException;
import br.com.mauda.seminario.cientificos.exception.SeminariosCientificosException;
import br.com.mauda.seminario.cientificos.model.interfaces.DataValidation;
import br.com.mauda.seminario.cientificos.util.StringUtils;

public class Curso implements DataValidation {
    private static final long serialVersionUID = 5L;

    private Long id;
    private String nome;
    private final AreaCientifica areaCientifica;

    public Curso(AreaCientifica areaCientifica) {
        areaCientifica.adicionarCurso(this);
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.id == null ? 0 : this.id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Curso other = (Curso) obj;
        if (this.id == null) {
            return other.id == null;
        } else return this.id.equals(other.id);
    }

    @Override
    public void validateForDataModification() {
        if (!StringUtils.isValidString(nome, 50)) {
            throw new SeminariosCientificosException("ER0020");
        }
        if (areaCientifica == null) {
            throw new ObjetoNuloException();
        }
        areaCientifica.validateForDataModification();
    }
}
