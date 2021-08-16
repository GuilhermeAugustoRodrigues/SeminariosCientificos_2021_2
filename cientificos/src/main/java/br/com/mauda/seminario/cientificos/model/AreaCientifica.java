package br.com.mauda.seminario.cientificos.model;

import java.util.ArrayList;
import java.util.List;

public class AreaCientifica {

    private Long id;
    private String nome;
    private List<Curso> cursos = new ArrayList<>();

    public void adicionarCurso(Curso curso) {
        curso.setAreaCientifica(this);
        cursos.add(curso);
    }

    public boolean possuiCurso(Curso curso) {
        return cursos.contains(curso);
    }

    public AreaCientifica() {}

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

    public List<Curso> getCursos() {
        return cursos;
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
        AreaCientifica other = (AreaCientifica) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
}
