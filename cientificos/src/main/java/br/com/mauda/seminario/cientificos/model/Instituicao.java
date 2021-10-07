package br.com.mauda.seminario.cientificos.model;

import br.com.mauda.seminario.cientificos.exception.SeminariosCientificosException;
import br.com.mauda.seminario.cientificos.model.interfaces.DataValidation;
import br.com.mauda.seminario.cientificos.util.StringUtils;

import javax.persistence.*;

@Entity
@Table(name = "TB_INSTITUICAO")
public class Instituicao implements DataValidation {
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sigla;
    private String cidade;
    private String estado;
    private String pais;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return this.sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return this.pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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
        Instituicao other = (Instituicao) obj;
        if (this.id == null) {
            return other.id == null;
        } else return this.id.equals(other.id);
    }

    @Override
    public void validateForDataModification() {
        String errorMessage = null;
        if (!StringUtils.isValidString(cidade, 50)) {
            errorMessage = "ER0050";
        }
        if (!StringUtils.isValidString(estado, 50)) {
            errorMessage = "ER0051";
        }
        if (!StringUtils.isValidString(nome, 100)) {
            errorMessage = "ER0052";
        }
        if (!StringUtils.isValidString(pais, 50)) {
            errorMessage = "ER0053";
        }
        if (!StringUtils.isValidString(sigla, 10)) {
            errorMessage = "ER0054";
        }
        if (errorMessage != null) {
            throw new SeminariosCientificosException(errorMessage);
        }
    }
}