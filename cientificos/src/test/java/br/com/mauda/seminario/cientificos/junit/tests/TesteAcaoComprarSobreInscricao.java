package br.com.mauda.seminario.cientificos.junit.tests;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertAll;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertEquals;

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
import br.com.mauda.seminario.cientificos.junit.massa.MassaInscricaoComprar;
import br.com.mauda.seminario.cientificos.model.Inscricao;
import br.com.mauda.seminario.cientificos.model.enums.SituacaoInscricaoEnum;
import br.com.mauda.seminario.cientificos.util.EnumUtils;

class TesteAcaoComprarSobreInscricao {

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
        this.acaoInscricaoDTO = TesteAcaoComprarSobreInscricao.converter.create(EnumUtils.getInstanceRandomly(MassaInscricaoComprar.class));
    }

    @DisplayName("Compra de uma inscricao para o Seminario")
    @ParameterizedTest(name = "Compra da inscricao [{arguments}] para o Seminario")
    @EnumSource(MassaInscricaoComprar.class)
    void comprarInscricao(@ConvertWith(AcaoInscricaoDTOConverter.class) AcaoInscricaoDTO dto) {
        Inscricao inscricao = dto.getInscricao();

        // Compra a inscricao pro seminario
        TesteAcaoComprarSobreInscricao.bc.comprar(inscricao, dto.getEstudante(), dto.getDireitoMaterial());

        // Verifica se a situacao da inscricao ficou como comprado
        assertEquals(inscricao.getSituacao(), SituacaoInscricaoEnum.COMPRADO,
            "Situacao da inscricao nao eh comprado - trocar a situacao no metodo comprar()");

        // Verifica se os atributos estao preenchidos
        assertAll(new InscricaoExecutable(inscricao));
    }
}