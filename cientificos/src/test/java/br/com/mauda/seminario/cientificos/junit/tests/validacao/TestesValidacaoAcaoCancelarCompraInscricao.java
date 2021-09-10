package br.com.mauda.seminario.cientificos.junit.tests.validacao;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertThrows;

import java.time.LocalDate;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.mauda.seminario.cientificos.bc.InscricaoBC;
import br.com.mauda.seminario.cientificos.exception.ObjetoNuloException;
import br.com.mauda.seminario.cientificos.junit.converter.dto.AcaoInscricaoDTOConverter;
import br.com.mauda.seminario.cientificos.junit.dto.AcaoInscricaoDTO;
import br.com.mauda.seminario.cientificos.junit.massa.MassaInscricaoCancelarCompra;
import br.com.mauda.seminario.cientificos.model.Inscricao;
import br.com.mauda.seminario.cientificos.model.enums.SituacaoInscricaoEnum;
import br.com.mauda.seminario.cientificos.util.EnumUtils;

class TestesValidacaoAcaoCancelarCompraInscricao {

    protected static InscricaoBC bc;
    protected static AcaoInscricaoDTOConverter converter;
    protected AcaoInscricaoDTO acaoInscricaoDTO;

    @BeforeAll
    static void beforeAll() {
        bc = InscricaoBC.getInstance();
        converter = new AcaoInscricaoDTOConverter();
    }

    @BeforeEach
    void beforeEach() {
        this.acaoInscricaoDTO = TestesValidacaoAcaoCancelarCompraInscricao.converter
            .create(EnumUtils.getInstanceRandomly(MassaInscricaoCancelarCompra.class));
    }

    @Test
    @DisplayName("Cancelar inscricao nula")
    void validarCompraComInscricaoNula() {
        assertThrows(ObjetoNuloException.class, () -> TestesValidacaoAcaoCancelarCompraInscricao.bc.cancelarCompra(null), "ER0003");
    }

    @Test
    @DisplayName("Cancelar inscricao com a situacao diferente de COMPRADO")
    void validarCompraComSituacaoInscricaoNaoDisponivel() throws IllegalAccessException {
        Inscricao inscricao = this.acaoInscricaoDTO.getInscricao();

        // Metodo que seta a situacao da inscricao como DISPONIVEL usando reflections
        FieldUtils.writeDeclaredField(inscricao, "situacao", SituacaoInscricaoEnum.DISPONIVEL, true);
        assertThrows(() -> TestesValidacaoAcaoCancelarCompraInscricao.bc.cancelarCompra(inscricao), "ER0044");

        // Metodo que seta a situacao da inscricao como CHECKIN usando reflections
        FieldUtils.writeDeclaredField(inscricao, "situacao", SituacaoInscricaoEnum.CHECKIN, true);
        assertThrows(() -> TestesValidacaoAcaoCancelarCompraInscricao.bc.cancelarCompra(inscricao), "ER0044");
    }

    @Test
    @DisplayName("Cancelar compra após a data do Seminario")
    void validarCancelamentoAposDataSeminario() {
        Inscricao inscricao = this.acaoInscricaoDTO.getInscricao();

        TestesValidacaoAcaoCancelarCompraInscricao.bc.comprar(inscricao, this.acaoInscricaoDTO.getEstudante(),
            this.acaoInscricaoDTO.getDireitoMaterial());

        // Diminui a data do seminario em 30 dias
        this.acaoInscricaoDTO.getSeminario().setData(LocalDate.now().minusDays(30));
        assertThrows(() -> TestesValidacaoAcaoCancelarCompraInscricao.bc.cancelarCompra(inscricao), "ER0045");
    }
}