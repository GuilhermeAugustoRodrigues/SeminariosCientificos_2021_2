package br.com.mauda.seminario.cientificos.bc;

import br.com.mauda.seminario.cientificos.exception.ObjetoNuloException;
import br.com.mauda.seminario.cientificos.exception.SeminariosCientificosException;
import br.com.mauda.seminario.cientificos.model.Estudante;
import br.com.mauda.seminario.cientificos.model.Inscricao;
import br.com.mauda.seminario.cientificos.model.enums.SituacaoInscricaoEnum;

import java.time.LocalDate;

public class InscricaoBC extends PatternCrudBC<Inscricao> {
    public static InscricaoBC getInstance() {
        return new InscricaoBC();
    }

    public static void comprar(Inscricao inscricao, Estudante estudante, Boolean direitoMaretial) {
        if (inscricao == null ||estudante == null ||direitoMaretial == null) {
            throw new ObjetoNuloException();
        }
        String error = null;
        if (inscricao.getSituacao() != SituacaoInscricaoEnum.DISPONIVEL) {
            error = "ER0042";
        }
        if (LocalDate.now().isAfter(inscricao.getSeminario().getData())) {
            error = "ER0043";
        }
        if (error != null) {
            throw new SeminariosCientificosException(error);
        }
        estudante.validateForDataModification();
        inscricao.comprar(estudante, direitoMaretial);
    }

    public static void cancelarCompra(Inscricao inscricao) {
        if (inscricao == null) {
            throw new ObjetoNuloException();
        }
        String error = null;
        if (inscricao.getSituacao() != SituacaoInscricaoEnum.COMPRADO) {
            error = "ER0044";
        }
        if (LocalDate.now().isAfter(inscricao.getSeminario().getData())) {
            error = "ER0045";
        }
        if (error != null) {
            throw new SeminariosCientificosException(error);
        }
        inscricao.cancelarCompra();
    }

    public static void realizarCheckIn(Inscricao inscricao) {
        if (inscricao == null) {
            throw new ObjetoNuloException();
        }
        String error = null;
        if (inscricao.getSituacao() != SituacaoInscricaoEnum.COMPRADO) {
            error = "ER0046";
        }
        if (LocalDate.now().isAfter(inscricao.getSeminario().getData())) {
            error = "ER0047";
        }
        if (error != null) {
            throw new SeminariosCientificosException(error);
        }
        inscricao.realizarCheckIn();
    }
}