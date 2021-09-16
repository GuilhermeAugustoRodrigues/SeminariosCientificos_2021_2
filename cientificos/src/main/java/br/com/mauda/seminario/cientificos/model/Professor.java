package br.com.mauda.seminario.cientificos.model;

import br.com.mauda.seminario.cientificos.exception.ObjetoNuloException;
import br.com.mauda.seminario.cientificos.exception.SeminariosCientificosException;
import br.com.mauda.seminario.cientificos.model.interfaces.DataValidation;
import br.com.mauda.seminario.cientificos.util.ListUtils;
import br.com.mauda.seminario.cientificos.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Professor implements DataValidation {
    private static final long serialVersionUID = 2L;

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private Double salario;
    private final Instituicao instituicao;
    private final List<Seminario> seminarios = new ArrayList<>();

    public Professor(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public void adicionarSeminario(Seminario seminario) {
        seminarios.add(seminario);
    }

    public boolean possuiSeminario(Seminario seminario) {
        return seminarios.contains(seminario);
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public List<Seminario> getSeminarios() {
        return seminarios;
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
        Professor other = (Professor) obj;
        if (this.id == null) {
            return other.id == null;
        } else return this.id.equals(other.id);
    }

    @Override
    public void validateForDataModification() {
        String errorMessage = validateFields();
        if (errorMessage != null) {
            throw new SeminariosCientificosException(errorMessage);
        }
        if (instituicao == null || !ListUtils.isValidList(seminarios, false)) {
            throw new ObjetoNuloException();
        }
        instituicao.validateForDataModification();
    }

    private String validateFields() {
        String errorMessage = null;
        if (!StringUtils.isValidEmail(email, 50)) {
            errorMessage = "ER0060";
        }
        if (!StringUtils.isValidString(nome, 50)) {
            errorMessage = "ER0061";
        }
        if (!StringUtils.isValidString(telefone, 15)) {
            errorMessage = "ER0062";
        }
        if (salario == null || salario <= 0) {
            errorMessage = "ER0063";
        }
        return errorMessage;
    }
}
