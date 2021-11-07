package br.com.mauda.seminario.cientificos.dto;

import br.com.mauda.seminario.cientificos.dto.util.ClassAttributeValidation;

import java.time.LocalDate;

public class SeminarioDTO implements FilterValidation {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate data;
    private Boolean mesaRedonda;
    private String nomeAreaCientifica;
    private String nomeProfessor;

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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Boolean getMesaRedonda() {
        return mesaRedonda;
    }

    public void setMesaRedonda(Boolean mesaRedonda) {
        this.mesaRedonda = mesaRedonda;
    }

    public String getNomeAreaCientifica() {
        return nomeAreaCientifica;
    }

    public void setNomeAreaCientifica(String nomeAreaCientifica) {
        this.nomeAreaCientifica = nomeAreaCientifica;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }
}
