package br.com.mauda.seminario.cientificos.model;

import br.com.mauda.seminario.cientificos.exception.ObjetoNuloException;
import br.com.mauda.seminario.cientificos.model.enums.SituacaoInscricaoEnum;
import br.com.mauda.seminario.cientificos.model.interfaces.DataValidation;

import java.time.LocalDateTime;

public class Inscricao implements DataValidation {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Boolean direitoMaterial;
    private final LocalDateTime dataCriacao;
    private LocalDateTime dataCompra;
    private LocalDateTime dataCheckIn;
    private Estudante estudante;
    private SituacaoInscricaoEnum situacao;
    private final Seminario seminario;

    public Inscricao(Seminario seminario) {
        this.seminario = seminario;
        dataCriacao = LocalDateTime.now();
        situacao = SituacaoInscricaoEnum.DISPONIVEL;
    }

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
        situacao = SituacaoInscricaoEnum.DISPONIVEL;
    }

    public void realizarCheckIn() {
        dataCheckIn = LocalDateTime.now();
        situacao = SituacaoInscricaoEnum.CHECKIN;
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

    public Seminario getSeminario() {
        return seminario;
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

    @Override
    public void validateForDataModification() {
        if (situacao == null || seminario == null ) {
            throw new ObjetoNuloException();
        }
        if (situacao != SituacaoInscricaoEnum.DISPONIVEL) {
            if (direitoMaterial == null || estudante == null) {
                throw new ObjetoNuloException();
            }
            estudante.validateForDataModification();
        }
    }
}
