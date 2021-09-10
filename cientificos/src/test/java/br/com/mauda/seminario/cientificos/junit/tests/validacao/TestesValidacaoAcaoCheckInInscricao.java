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
import br.com.mauda.seminario.cientificos.junit.massa.MassaInscricaoComprar;
import br.com.mauda.seminario.cientificos.model.Inscricao;
import br.com.mauda.seminario.cientificos.model.enums.SituacaoInscricaoEnum;
import br.com.mauda.seminario.cientificos.util.EnumUtils;

class TestesValidacaoAcaoCheckInInscricao {

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
        this.acaoInscricaoDTO = TestesValidacaoAcaoCheckInInscricao.converter.create(EnumUtils.getInstanceRandomly(MassaInscricaoComprar.class));
    }

    @Test
    @DisplayName("CheckIn de uma inscricao nula")
    void validarCompraComInscricaoNula() {
        assertThrows(ObjetoNuloException.class, () -> TestesValidacaoAcaoCheckInInscricao.bc.realizarCheckIn(null), "ER0003");
    }

    @Test
    @DisplayName("CheckIn de uma inscricao com a situacao diferente de COMPRADO")
    void validarCompraComSituacaoInscricaoNaoDisponivel() throws IllegalAccessException {
        Inscricao inscricao = this.acaoInscricaoDTO.getInscricao();

        // Metodo que seta a situacao da inscricao como DISPONIVEL usando reflections
        FieldUtils.writeDeclaredField(inscricao, "situacao", SituacaoInscricaoEnum.DISPONIVEL, true);
        assertThrows(() -> TestesValidacaoAcaoCheckInInscricao.bc.realizarCheckIn(inscricao), "ER0046");

        // Metodo que seta a situacao da inscricao como CHECKIN usando reflections
        FieldUtils.writeDeclaredField(inscricao, "situacao", SituacaoInscricaoEnum.CHECKIN, true);
        assertThrows(() -> TestesValidacaoAcaoCheckInInscricao.bc.realizarCheckIn(inscricao), "ER0046");
    }

    @Test
    @DisplayName("CheckIn de uma inscricao após a data do Seminario")
    void validarCheckInAposDataSeminario() {
        Inscricao inscricao = this.acaoInscricaoDTO.getInscricao();

        TestesValidacaoAcaoCheckInInscricao.bc.comprar(inscricao, this.acaoInscricaoDTO.getEstudante(), this.acaoInscricaoDTO.getDireitoMaterial());

        // Diminui a data do seminario em 30 dias
        this.acaoInscricaoDTO.getSeminario().setData(LocalDate.now().minusDays(30));
        assertThrows(() -> TestesValidacaoAcaoCheckInInscricao.bc.realizarCheckIn(inscricao), "ER0047");
    }
}