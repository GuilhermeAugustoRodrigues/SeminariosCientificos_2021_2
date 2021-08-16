package br.com.mauda.seminario.cientificos.model;

import br.com.mauda.seminario.cientificos.model.enums.SituacaoInscricaoEnum;

import java.time.LocalDateTime;

public class Inscricao {

    private Long id;
    private boolean direitoMaterial;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataCompra;
    private LocalDateTime dataCheckIn;
    private Estudante estudante;
    private SituacaoInscricaoEnum situacao;
    private Seminario seminario;

    public void comprar(Estudante estudante, boolean direitoMaterial) {
        this.estudante = estudante;
        this.direitoMaterial = direitoMaterial;
        this.situacao = SituacaoInscricaoEnum.COMPRADO;
    }

    public void cancelarCompra() {
        this.estudante = null;
        this.direitoMaterial = false;
        this.situacao = SituacaoInscricaoEnum.DISPONIVEL;
    }

    public void realizarCheckIn() {
        this.situacao = SituacaoInscricaoEnum.CHECKIN;
    }

    public Inscricao(Long id, boolean direitoMaterial, LocalDateTime dataCriacao, LocalDateTime dataCompra, LocalDateTime dataCheckIn) {
        this.id = id;
        this.direitoMaterial = direitoMaterial;
        this.dataCriacao = dataCriacao;
        this.dataCompra = dataCompra;
        this.dataCheckIn = dataCheckIn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getDireitoMaterial() {
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
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
}
