package br.com.mauda.seminario.cientificos.model;

import java.util.ArrayList;
import java.util.List;

public class Estudante {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private final List<Inscricao> inscricoes = new ArrayList<>();
    private Instituicao instituicao;

    public Estudante(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public void adicionarInscricao(Inscricao inscricao) {
        inscricoes.add(inscricao);
    }

    public boolean possuiInscricao(Inscricao inscricao) {
        return inscricoes.contains(inscricao);
    }

    public void removerInscricao(Inscricao inscricao) {
        inscricoes.remove(inscricao);
    }

    public Estudante() {}

    public Estudante(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
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

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Instituicao getInstituicao() {
        return instituicao;
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
        Estudante other = (Estudante) obj;
        if (this.id == null) {
            return other.id == null;
        } else return this.id.equals(other.id);
    }
}