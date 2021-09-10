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
import br.com.mauda.seminario.cientificos.junit.massa.MassaInscricaoCheckIn;
import br.com.mauda.seminario.cientificos.junit.massa.MassaInscricaoComprar;
import br.com.mauda.seminario.cientificos.model.Inscricao;
import br.com.mauda.seminario.cientificos.model.enums.SituacaoInscricaoEnum;
import br.com.mauda.seminario.cientificos.util.EnumUtils;

class TesteAcaoCheckInSobreInscricao {

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
        this.acaoInscricaoDTO = TesteAcaoCheckInSobreInscricao.converter.create(EnumUtils.getInstanceRandomly(MassaInscricaoComprar.class));
    }

    @DisplayName("CheckIn de uma inscricao para o Seminario")
    @ParameterizedTest(name = "CheckIn da inscricao [{arguments}] para o Seminario")
    @EnumSource(MassaInscricaoCheckIn.class)
    void checkInscricao(@ConvertWith(AcaoInscricaoDTOConverter.class) AcaoInscricaoDTO object) {
        Inscricao inscricao = object.getInscricao();

        // Compra a inscricao pro seminario
        TesteAcaoCheckInSobreInscricao.bc.comprar(inscricao, object.getEstudante(), object.getDireitoMaterial());

        this.validarCompra(inscricao);

        // Realiza o check in da inscricao pro seminario
        TesteAcaoCheckInSobreInscricao.bc.realizarCheckIn(inscricao);

        // Verifica se os atributos estao preenchidos
        assertAll(new InscricaoExecutable(inscricao));

        // Verifica se a situacao da inscricao ficou como comprado
        assertEquals(inscricao.getSituacao(), SituacaoInscricaoEnum.CHECKIN,
            "Situacao da inscricao nao eh checkIn - trocar a situacao no metodo realizarCheckIn()");
    }

    private void validarCompra(Inscricao inscricao) {
        // Verifica se os atributos estao preenchidos
        assertAll(new InscricaoExecutable(inscricao));

        // Verifica se a situacao da inscricao ficou como comprado
        assertEquals(inscricao.getSituacao(), SituacaoInscricaoEnum.COMPRADO,
            "Situacao da inscricao nao eh comprado - trocar a situacao no metodo comprar()");
    }
}