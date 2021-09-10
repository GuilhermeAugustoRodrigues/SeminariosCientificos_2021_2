package br.com.mauda.seminario.cientificos.junit.tests;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertAll;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.EnumSource;

import br.com.mauda.seminario.cientificos.bc.InscricaoBC;
import br.com.mauda.seminario.cientificos.junit.converter.dto.AcaoInscricaoDTOConverter;
import br.com.mauda.seminario.cientificos.junit.dto.AcaoInscricaoDTO;
import br.com.mauda.seminario.cientificos.junit.executable.InscricaoExecutable;
import br.com.mauda.seminario.cientificos.junit.massa.MassaInscricaoCancelarCompra;
import br.com.mauda.seminario.cientificos.model.Inscricao;
import br.com.mauda.seminario.cientificos.model.enums.SituacaoInscricaoEnum;
import br.com.mauda.seminario.cientificos.util.EnumUtils;

class TesteAcaoCancelarCompraSobreInscricao {

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
        this.acaoInscricaoDTO = TesteAcaoCancelarCompraSobreInscricao.converter
            .create(EnumUtils.getInstanceRandomly(MassaInscricaoCancelarCompra.class));
    }

    @DisplayName("Cancelar uma inscricao para o Seminario")
    @ParameterizedTest(name = "Cancelar inscricao [{arguments}] para o Seminario")
    @EnumSource(MassaInscricaoCancelarCompra.class)
    void cancelarCompra(@ConvertWith(AcaoInscricaoDTOConverter.class) AcaoInscricaoDTO object) {
        Inscricao inscricao = object.getInscricao();

        // Compra a inscricao pro seminario
        TesteAcaoCancelarCompraSobreInscricao.bc.comprar(inscricao, object.getEstudante(), object.getDireitoMaterial());

        this.validarCompra(inscricao);

        // Realiza o cancelamento da inscricao pro seminario
        TesteAcaoCancelarCompraSobreInscricao.bc.cancelarCompra(inscricao);

        // Verifica se a inscricao foi removida do estudante
        assertEquals(inscricao.getSituacao(), SituacaoInscricaoEnum.DISPONIVEL,
            "Situacao da inscricao nao eh Disponivel - trocar a situacao no metodo cancelarCompra()");

        // Verifica se os atributos estao preenchidos
        assertAll(new InscricaoExecutable(inscricao));

        assertFalse(object.getEstudante().possuiInscricao(inscricao),
            "Estudante nao deveria possuir a inscricao - remover no metodo cancelarCompra()");
    }

    private void validarCompra(Inscricao inscricao) {
        // Verifica se a situacao da inscricao ficou como comprado
        assertEquals(inscricao.getSituacao(), SituacaoInscricaoEnum.COMPRADO,
            "Situacao da inscricao nao eh comprado - trocar a situacao no metodo comprar()");

        // Verifica se os atributos estao preenchidos
        assertAll(new InscricaoExecutable(inscricao));
    }
}