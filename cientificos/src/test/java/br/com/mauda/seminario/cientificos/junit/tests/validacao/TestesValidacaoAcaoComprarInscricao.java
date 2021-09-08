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
import br.com.mauda.seminario.cientificos.model.Estudante;
import br.com.mauda.seminario.cientificos.model.Inscricao;
import br.com.mauda.seminario.cientificos.model.enums.SituacaoInscricaoEnum;
import br.com.mauda.seminario.cientificos.util.EnumUtils;

class TestesValidacaoAcaoComprarInscricao {

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
        this.acaoInscricaoDTO = TestesValidacaoAcaoComprarInscricao.converter.create(EnumUtils.getInstanceRandomly(MassaInscricaoComprar.class));
    }

    @Test
    @DisplayName("Compra com inscricao nula")
    void validarCompraComInscricaoNula() {
        Estudante estudante = this.acaoInscricaoDTO.getEstudante();
        Boolean direitoMaterial = this.acaoInscricaoDTO.getDireitoMaterial();
        assertThrows(ObjetoNuloException.class, () -> TestesValidacaoAcaoComprarInscricao.bc.comprar(null, estudante, direitoMaterial), "ER0003");
    }

    @Test
    @DisplayName("Compra com estudante nulo")
    void validarCompraComEstudanteNulo() {
        Inscricao inscricao = this.acaoInscricaoDTO.getInscricao();
        Boolean direitoMaterial = this.acaoInscricaoDTO.getDireitoMaterial();
        assertThrows(ObjetoNuloException.class, () -> TestesValidacaoAcaoComprarInscricao.bc.comprar(inscricao, null, direitoMaterial), "ER0003");
    }

    @Test
    @DisplayName("Compra com direito material nulo")
    void validarCompraComDireitoMaterialNulo() {
        Inscricao inscricao = this.acaoInscricaoDTO.getInscricao();
        Estudante estudante = this.acaoInscricaoDTO.getEstudante();
        assertThrows(ObjetoNuloException.class, () -> TestesValidacaoAcaoComprarInscricao.bc.comprar(inscricao, estudante, null), "ER0003");
    }

    @Test
    @DisplayName("Compra com situacao da inscricao diferente de Disponivel")
    void validarCompraComSituacaoInscricaoNaoDisponivel() throws IllegalAccessException {
        Inscricao inscricao = this.acaoInscricaoDTO.getInscricao();
        Estudante estudante = this.acaoInscricaoDTO.getEstudante();
        Boolean direitoMaterial = this.acaoInscricaoDTO.getDireitoMaterial();

        // Metodo que seta a situacao da inscricao como COMPRADO usando reflections
        FieldUtils.writeDeclaredField(inscricao, "situacao", SituacaoInscricaoEnum.COMPRADO, true);
        assertThrows(() -> TestesValidacaoAcaoComprarInscricao.bc.comprar(inscricao, estudante, direitoMaterial), "ER0042");

        // Metodo que seta a situacao da inscricao como CHECKIN usando reflections
        FieldUtils.writeDeclaredField(inscricao, "situacao", SituacaoInscricaoEnum.CHECKIN, true);
        assertThrows(() -> TestesValidacaoAcaoComprarInscricao.bc.comprar(inscricao, estudante, direitoMaterial), "ER0042");
    }

    @Test
    @DisplayName("Compra de uma inscricao após a data do Seminario")
    void validarCompraAposDataSeminario() {
        Inscricao inscricao = this.acaoInscricaoDTO.getInscricao();
        Estudante estudante = this.acaoInscricaoDTO.getEstudante();
        Boolean direitoMaterial = this.acaoInscricaoDTO.getDireitoMaterial();

        // Diminui a data do seminario em 30 dias
        this.acaoInscricaoDTO.getSeminario().setData(LocalDate.now().minusDays(30));

        assertThrows(() -> TestesValidacaoAcaoComprarInscricao.bc.comprar(inscricao, estudante, direitoMaterial), "ER0043");
    }
}