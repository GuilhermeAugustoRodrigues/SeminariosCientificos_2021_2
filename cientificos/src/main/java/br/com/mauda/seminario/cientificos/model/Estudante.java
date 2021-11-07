package br.com.mauda.seminario.cientificos.model;

import br.com.mauda.seminario.cientificos.exception.ObjetoNuloException;
import br.com.mauda.seminario.cientificos.exception.SeminariosCientificosException;
import br.com.mauda.seminario.cientificos.model.interfaces.DataValidation;
import br.com.mauda.seminario.cientificos.util.ListUtils;
import br.com.mauda.seminario.cientificos.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_ESTUDANTE")
public class Estudante implements DataValidation {
    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;

    @OneToMany(mappedBy = "estudante")
    private final List<Inscricao> inscricoes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "ID_INSTITUICAO")
    private Instituicao instituicao;

    public Estudante(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    private Estudante() {}

    public void adicionarInscricao(Inscricao inscricao) {
        inscricoes.add(inscricao);
    }

    public boolean possuiInscricao(Inscricao inscricao) {
        return inscricoes.contains(inscricao);
    }

    public void removerInscricao(Inscricao inscricao) {
        inscricoes.remove(inscricao);
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

    public List<Inscricao> getInscricoes() {
        return inscricoes;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
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
        Estudante other = (Estudante) obj;
        if (this.id == null) {
            return other.id == null;
        } else return this.id.equals(other.id);
    }

    @Override
    public void validateForDataModification() {
        String errorMessage = null;
        if (!StringUtils.isValidEmail(email, 50)) {
            errorMessage = "ER0030";
        }
        if (!StringUtils.isValidString(nome, 50)) {
            errorMessage = "ER0031";
        }
        if (!StringUtils.isValidString(telefone, 15)) {
            errorMessage = "ER0032";
        }
        if (errorMessage != null) {
            throw new SeminariosCientificosException(errorMessage);
        }
        if (instituicao == null || !ListUtils.isValidList(inscricoes, false)) {
            throw new ObjetoNuloException();
        }
        instituicao.validateForDataModification();
    }
}
