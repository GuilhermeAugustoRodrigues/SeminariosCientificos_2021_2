package br.com.mauda.seminario.cientificos.junit.executable;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertAll;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertEquals;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertNotNull;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertNull;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.function.Executable;

import br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao;
import br.com.mauda.seminario.cientificos.model.Inscricao;
import br.com.mauda.seminario.cientificos.model.enums.SituacaoInscricaoEnum;

public class InscricaoExecutable implements Executable {

    private Inscricao inscricao;
    private MassaInscricao inscricaoEnum;

    public InscricaoExecutable(Inscricao inscricao) {
        this.inscricao = inscricao;
    }

    public InscricaoExecutable(Inscricao inscricao, MassaInscricao enumm) {
        this(inscricao);
        this.inscricaoEnum = enumm;
    }

    public void basicVerification(Inscricao inscricao) throws Throwable {
        assertNotNull(inscricao, "Uma Inscricao nao pode ser nula");
        assertNotNull(inscricao.getSituacao(), "A situacao de uma Inscricao nao pode ser nula");
        assertNotNull(inscricao.getDataCriacao(), "A inscricao não possui uma data de criacao");
        assertTrue(LocalDateTime.now().isAfter(inscricao.getDataCriacao()), "A inscricao não pode possuir uma data de criacao no futuro");

        if (SituacaoInscricaoEnum.DISPONIVEL.equals(inscricao.getSituacao())) {
            assertNull(inscricao.getEstudante(), "Um estudante nao deve estar atribuido a uma inscricao disponivel");
            assertNull(inscricao.getDireitoMaterial(), "O direito ao material de uma Inscricao disponivel deve ser nulo");
            assertNull(inscricao.getDataCompra(), "A inscricao não deve possuir uma data de compra");
            assertNull(inscricao.getDataCheckIn(), "A inscricao não deve possuir um data de checkin");

        } else {
            assertNotNull(inscricao.getDireitoMaterial(), "O direito ao material de uma Inscricao nao pode ser nulo");

            // Verifica se o estudante dentro do inscricao esta preenchido corretamente
            assertAll(new EstudanteExecutable(inscricao.getEstudante()));

            // Verifica a associacao bidirecional com estudante
            assertTrue(inscricao.getEstudante().possuiInscricao(inscricao), "A Lista de inscricoes do Estudante "
                + inscricao.getEstudante().getNome() + " nao contem a inscricao em questao - associacao bidirecional nao foi realizada");

            assertNotNull(inscricao.getDataCompra(), "A inscricao deve possuir uma data de compra");
            assertTrue(LocalDateTime.now().isAfter(inscricao.getDataCriacao()), "A inscricao não pode possuir uma data de compra no futuro");

            if (SituacaoInscricaoEnum.COMPRADO.equals(inscricao.getSituacao())) {
                assertNull(inscricao.getDataCheckIn(), "A inscricao não deve possuir um data de checkin");
            } else {
                assertNotNull(inscricao.getDataCheckIn(), "A inscricao deve possuir um data de checkin");
                assertTrue(LocalDateTime.now().isAfter(inscricao.getDataCriacao()), "A inscricao não pode possuir uma data de checkin no futuro");
            }
        }

        assertAll(new SeminarioExecutable(inscricao.getSeminario()));
    }

    @Override
    public void execute() throws Throwable {
        this.basicVerification(this.inscricao);

        if (this.inscricaoEnum != null) {
            assertEquals(this.inscricaoEnum.isDireitoMaterial(), this.inscricao.getDireitoMaterial(),
                "Direito ao Material das inscricoes nao sao iguais");
            assertEquals(this.inscricaoEnum.getSituacao(), this.inscricao.getSituacao(), "Situacao das inscricoes nao sao iguais");

            // SENAO for a situacao disponivel
            if (!SituacaoInscricaoEnum.DISPONIVEL.equals(this.inscricao.getSituacao())) {
                assertAll(new EstudanteExecutable(this.inscricao.getEstudante(), this.inscricaoEnum.getEstudante()));
            }
            assertAll(new SeminarioExecutable(this.inscricao.getSeminario(), this.inscricaoEnum.getSeminario()));
            return;
        }
    }
}
