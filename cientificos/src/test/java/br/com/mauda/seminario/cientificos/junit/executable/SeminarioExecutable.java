package br.com.mauda.seminario.cientificos.junit.executable;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertAll;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertEquals;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertIsNotBlank;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertNotNull;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertTrue;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.fail;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.function.Executable;

import br.com.mauda.seminario.cientificos.junit.massa.MassaSeminario;
import br.com.mauda.seminario.cientificos.model.AreaCientifica;
import br.com.mauda.seminario.cientificos.model.Inscricao;
import br.com.mauda.seminario.cientificos.model.Professor;
import br.com.mauda.seminario.cientificos.model.Seminario;

public class SeminarioExecutable implements Executable {

    private Seminario seminario;
    private MassaSeminario seminarioEnum;

    public SeminarioExecutable(Seminario seminario) {
        this.seminario = seminario;
    }

    public SeminarioExecutable(Seminario seminario, MassaSeminario enumm) {
        this(seminario);
        this.seminarioEnum = enumm;
    }

    public void basicVerification(Seminario seminario) throws Throwable {
        assertNotNull(seminario, "Um Seminario nao pode ser nulo");
        assertNotNull(seminario.getAreasCientificas(), "É necessário inicializar a lista de areas cientificas");
        assertNotNull(seminario.getInscricoes(), "É necessário inicializar a lista de inscricoes");
        assertNotNull(seminario.getProfessores(), "É necessário inicializar a lista de professores");
        assertNotNull(seminario.getData(), "A data de um Seminario nao pode ser nula");
        assertNotNull(seminario.getQtdInscricoes(), "A quantidade de inscricoes de um Seminario nao pode ser nulo");

        assertTrue(seminario.getQtdInscricoes() > 0, "A quantidade de inscricoes de um Seminario deve ser maior que zero");
        assertIsNotBlank(seminario.getDescricao(), "A descricao de um Seminario nao pode ser nulo ou em branco");
        assertIsNotBlank(seminario.getTitulo(), "O titulo de um Seminario nao pode ser nulo ou em branco");

        for (Professor professor : seminario.getProfessores()) {
            assertAll(new ProfessorExecutable(professor));

            // Verifica a associacao bidirecional com professor
            assertTrue(professor.possuiSeminario(seminario), "A lista de Seminarios do Professor "
                + professor.getNome() + " nao contem o seminario em questao - associacao bidirecional no construtor de Seminarios nao foi realizada");
        }

        for (AreaCientifica area : seminario.getAreasCientificas()) {
            assertAll(new AreaCientificaExecutable(area));
        }

        // Verifica se a lista de inscricoes contem a quantidade gerada
        assertEquals(seminario.getQtdInscricoes(), new Integer(seminario.getInscricoes().size()),
            "A lista de inscricoes nao contem todas as inscricoes de acordo com a quantidade estipulada");

        for (Inscricao inscricao : seminario.getInscricoes()) {
            // Verifica a associacao bidirecional com inscricao
            assertEquals(inscricao.getSeminario(), seminario,
                "A inscricao nao contem o seminario em questao - associacao bidirecional no construtor de Seminarios nao foi realizada");

            assertNotNull(inscricao.getDataCriacao(), "A inscricao não possui uma data de criacao");
            assertTrue(LocalDateTime.now().isAfter(inscricao.getDataCriacao()), "A inscricao não pode possuir uma data de criacao no futuro");
        }
    }

    @Override
    public void execute() throws Throwable {
        this.basicVerification(this.seminario);

        if (this.seminarioEnum != null) {
            assertTrue(this.seminarioEnum.getData().equals(this.seminario.getData()), "Datas dos seminarios nao sao iguais");
            assertEquals(this.seminarioEnum.getDescricao(), this.seminario.getDescricao(), "Descricao dos seminarios nao sao iguais");
            assertEquals(this.seminarioEnum.getQtdInscricoes(), this.seminario.getQtdInscricoes(), "Quantidade de inscricoes nao sao iguais");
            assertEquals(this.seminarioEnum.getTitulo(), this.seminario.getTitulo(), "Titulo dos seminarios nao sao iguais");

            AreaCientifica areaCientifica = this.obtemAreaCientificaPeloNome(this.seminario.getAreasCientificas(),
                this.seminarioEnum.getAreaCientifica().getNome());
            assertAll(new AreaCientificaExecutable(areaCientifica, this.seminarioEnum.getAreaCientifica()));

            Professor professor = this.obtemProfessorPeloNome(this.seminario.getProfessores(), this.seminarioEnum.getProfessor().getNome());
            assertAll(new ProfessorExecutable(professor, this.seminarioEnum.getProfessor()));

            return;
        }
    }

    private AreaCientifica obtemAreaCientificaPeloNome(List<AreaCientifica> instancias, String nome) {
        AreaCientifica areaCientifica = instancias.stream()
            .filter(a -> nome.equals(a.getNome()))
            .findAny()
            .orElse(null);

        if (areaCientifica == null) {
            fail("Nao encontrou Area Cientifica correspondente");
        }
        return areaCientifica;
    }

    private Professor obtemProfessorPeloNome(List<Professor> instancias, String nome) {
        Professor professor = instancias.stream()
            .filter(p -> nome.equals(p.getNome()))
            .findAny()
            .orElse(null);

        if (professor == null) {
            fail("Nao encontrou professor correspondente");
        }
        return professor;
    }
}