package br.com.mauda.seminario.cientificos.dto;

import br.com.mauda.seminario.cientificos.dto.util.ClassAttributeValidation;
import br.com.mauda.seminario.cientificos.model.enums.SituacaoInscricaoEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InscricaoDTO implements FilterValidation {
    private Long id;
    private String titulo;
    private LocalDate dataSeminario;
    private Boolean direitoMaterial;
    private String nomeEstudante;
    private List<SituacaoInscricaoEnum> situacoes = new ArrayList<>();

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

    public String getTituloSeminario() {
        return titulo;
    }

    public void setTituloSeminario(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataSeminario() {
        return dataSeminario;
    }

    public void setDataSeminario(LocalDate dataSeminario) {
        this.dataSeminario = dataSeminario;
    }

    public Boolean getDireitoMaterial() {
        return direitoMaterial;
    }

    public void setDireitoMaterial(Boolean direitoMaterial) {
        this.direitoMaterial = direitoMaterial;
    }

    public String getNomeEstudante() {
        return nomeEstudante;
    }

    public void setNomeEstudante(String nomeEstudante) {
        this.nomeEstudante = nomeEstudante;
    }

    public List<SituacaoInscricaoEnum> getSituacoes() {
        return situacoes;
    }

    public void setSituacoes(List<SituacaoInscricaoEnum> situacoes) {
        this.situacoes = situacoes;
    }
}
