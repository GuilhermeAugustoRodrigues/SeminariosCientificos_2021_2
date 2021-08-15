package br.com.mauda.seminario.cientificos.junit.dto;

import br.com.mauda.seminario.cientificos.model.Estudante;
import br.com.mauda.seminario.cientificos.model.Inscricao;
import br.com.mauda.seminario.cientificos.model.Seminario;

public class AcaoInscricaoDTO {

    private Inscricao inscricao;
    private Seminario seminario;
    private Estudante estudante;
    private Boolean direitoMaterial;

    public AcaoInscricaoDTO(Seminario seminario, Estudante estudante, Inscricao inscricao, Boolean direitoMaterial) {
        this.seminario = seminario;
        this.estudante = estudante;
        this.inscricao = inscricao;
        this.direitoMaterial = direitoMaterial;
    }

    public Inscricao getInscricao() {
        return this.inscricao;
    }

    public Seminario getSeminario() {
        return this.seminario;
    }

    public Estudante getEstudante() {
        return this.estudante;
    }

    public Boolean getDireitoMaterial() {
        return this.direitoMaterial;
    }
}