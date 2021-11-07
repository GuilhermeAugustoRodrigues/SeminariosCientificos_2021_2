package br.com.mauda.seminario.cientificos.bc;

import br.com.mauda.seminario.cientificos.dao.InscricaoDAO;
import br.com.mauda.seminario.cientificos.dto.InscricaoDTO;
import br.com.mauda.seminario.cientificos.exception.ObjetoNuloException;
import br.com.mauda.seminario.cientificos.exception.SeminariosCientificosException;
import br.com.mauda.seminario.cientificos.model.Estudante;
import br.com.mauda.seminario.cientificos.model.Inscricao;
import br.com.mauda.seminario.cientificos.model.enums.SituacaoInscricaoEnum;

import java.time.LocalDate;

public class InscricaoBC extends PatternCrudBC<Inscricao, InscricaoDTO, InscricaoDAO> {
    public static InscricaoBC getInstance() {return instance;}

    private static final InscricaoBC instance = new InscricaoBC();

    private InscricaoBC() {
        this.dao = InscricaoDAO.getInstance();
    }

    public void comprar(Inscricao inscricao, Estudante estudante, Boolean direitoMaterial) {
        if (inscricao == null ||estudante == null ||direitoMaterial == null) {
            throw new ObjetoNuloException();
        }
        String error = null;
        if (!SituacaoInscricaoEnum.DISPONIVEL.equals(inscricao.getSituacao())) {
            error = "ER0042";
        }
        if (LocalDate.now().isAfter(inscricao.getSeminario().getData())) {
            error = "ER0043";
        }
        if (error != null) {
            throw new SeminariosCientificosException(error);
        }
        estudante.validateForDataModification();
        inscricao.comprar(estudante, direitoMaterial);

        this.update(inscricao);
    }

    public void cancelarCompra(Inscricao inscricao) {
        if (inscricao == null) {
            throw new ObjetoNuloException();
        }
        String error = null;
        if (!SituacaoInscricaoEnum.COMPRADO.equals(inscricao.getSituacao())) {
            error = "ER0044";
        }
        if (LocalDate.now().isAfter(inscricao.getSeminario().getData())) {
            error = "ER0045";
        }
        if (error != null) {
            throw new SeminariosCientificosException(error);
        }
        inscricao.cancelarCompra();

        this.update(inscricao);
    }

    public void realizarCheckIn(Inscricao inscricao) {
        if (inscricao == null) {
            throw new ObjetoNuloException();
        }
        String error = null;
        if (!SituacaoInscricaoEnum.COMPRADO.equals(inscricao.getSituacao())) {
            error = "ER0046";
        }
        if (LocalDate.now().isAfter(inscricao.getSeminario().getData())) {
            error = "ER0047";
        }
        if (error != null) {
            throw new SeminariosCientificosException(error);
        }
        inscricao.realizarCheckIn();

        this.update(inscricao);
    }
}