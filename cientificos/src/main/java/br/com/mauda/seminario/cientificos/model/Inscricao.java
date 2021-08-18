package br.com.mauda.seminario.cientificos.model;

import br.com.mauda.seminario.cientificos.model.enums.SituacaoInscricaoEnum;

import java.time.LocalDateTime;

public class Inscricao {

    private Long id;
    private Boolean direitoMaterial;
    private final LocalDateTime dataCriacao;
    private LocalDateTime dataCompra;
    private LocalDateTime dataCheckIn;
    private Estudante estudante;
    private SituacaoInscricaoEnum situacao;
    private Seminario seminario;

    public void comprar(Estudante estudante, boolean direitoMaterial) {
        this.estudante = estudante;
        this.direitoMaterial = direitoMaterial;
        situacao = SituacaoInscricaoEnum.COMPRADO;
        dataCompra = LocalDateTime.now();
        estudante.adicionarInscricao(this);
    }

    public void cancelarCompra() {
        estudante.removerInscricao(this);
        estudante = null;
        direitoMaterial = null;
        dataCompra = null;
        dataCheckIn = null;
        situacao = SituacaoInscricaoEnum.DISPONIVEL;
    }

    public void realizarCheckIn() {
        dataCheckIn = LocalDateTime.now();
        situacao = SituacaoInscricaoEnum.CHECKIN;
    }

    public Inscricao(Boolean direitoMaterial, LocalDateTime dataCriacao, LocalDateTime dataCompra, LocalDateTime dataCheckIn) {
        this.direitoMaterial = direitoMaterial;
        this.dataCriacao = dataCriacao;
        this.dataCompra = dataCompra;
        this.dataCheckIn = dataCheckIn;
        situacao = SituacaoInscricaoEnum.DISPONIVEL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDireitoMaterial() {
        return direitoMaterial;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public LocalDateTime getDataCheckIn() {
        return dataCheckIn;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public Seminario getSeminario() {
        return seminario;
    }

    public void setSeminario(Seminario seminario) {
        this.seminario = seminario;
    }

    public SituacaoInscricaoEnum getSituacao() {
        return situacao;
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
        Inscricao other = (Inscricao) obj;
        if (this.id == null) {
            return other.id == null;
        } else return this.id.equals(other.id);
    }
}
