package br.com.mauda.seminario.cientificos.junit.tests.queries;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertAll;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import br.com.mauda.seminario.cientificos.bc.ProfessorBC;
import br.com.mauda.seminario.cientificos.dto.ProfessorDTO;
import br.com.mauda.seminario.cientificos.junit.executable.ProfessorExecutable;
import br.com.mauda.seminario.cientificos.junit.massa.MassaProfessor;
import br.com.mauda.seminario.cientificos.junit.provider.FindAllSource;
import br.com.mauda.seminario.cientificos.model.Professor;

class TesteProfessorQueries {

    protected static ProfessorBC bc;

    @BeforeAll
    static void beforeAll() {
        bc = ProfessorBC.getInstance();
    }

    @DisplayName("Pesquisa de um Professor pelos metodos findAll e findById")
    @ParameterizedTest(name = "Pesquisa do Professor [{arguments}] pelos metodos findAll e findById")
    @FindAllSource(value = ProfessorBC.class)
    void pesquisar(Professor objetoFindAll) {
        // Busca pelo FindById
        Professor objetoFindId = TesteProfessorQueries.bc.findById(objetoFindAll.getId());

        // Realiza as verificacoes entre o objeto obtido pelo metodo findAll e o objeto obtido pelo findById
        assertAll(new ProfessorExecutable(objetoFindAll, objetoFindId));

        ProfessorDTO filter = new ProfessorDTO();
        // Seta a informacao do filtro
        filter.setId(objetoFindAll.getId());

        // Obtem as informacoes do banco de dados
        Collection<Professor> objetosFindByFilter = TesteProfessorQueries.bc.findByFilter(filter);

        assertEquals(1, objetosFindByFilter.size(), "O metodo findByFilter deveria ter retornado apenas 1 resultado, ao buscar pelo ID.");

        // Verifica se os objetos sao iguais
        assertAll(new ProfessorExecutable(objetoFindAll, objetosFindByFilter.iterator().next()));
    }

    /**
     * Realiza um teste com o filtro nulo, esperando que ocorram problemas
     */
    @Test
    @DisplayName("FindByFilter utilizando um filtro nulo")
    void validarNulo() {
        assertThrows(() -> TesteProfessorQueries.bc.findByFilter(null), "ER0001");
    }

    @Test
    @DisplayName("FindByFilter utilizando um filtro vazio")
    void validarFiltroVazio() {
        assertThrows(() -> TesteProfessorQueries.bc.findByFilter(new ProfessorDTO()), "ER0001");
    }

    @DisplayName("FindByFilter utilizando um filtro com o email do Professor")
    @ParameterizedTest(name = "Pesquisa do Professor a partir do email [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaProfessor.class)
    void validarFiltroEmailProfessor(MassaProfessor massa) {
        ProfessorDTO filter = new ProfessorDTO();
        filter.setEmail(massa.getEmail());

        // Obtem as informacoes do banco de dados
        Collection<Professor> results = TesteProfessorQueries.bc.findByFilter(filter);
        assertEquals(1, results.size(), "O metodo findByFilter deveria ter retornado apenas 1 resultado, favor deletar os itens duplicados");

        assertAll(new ProfessorExecutable(results.iterator().next(), massa));
    }

    @DisplayName("FindByFilter utilizando um filtro com o nome do Professor")
    @ParameterizedTest(name = "Pesquisa do Professor a partir do nome [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaProfessor.class)
    void validarFiltroNomeProfessor(MassaProfessor massa) {
        ProfessorDTO filter = new ProfessorDTO();
        filter.setNome(massa.getNome());

        // Obtem as informacoes do banco de dados
        Collection<Professor> results = TesteProfessorQueries.bc.findByFilter(filter);
        assertEquals(1, results.size(), "O metodo findByFilter deveria ter retornado apenas 1 resultado, favor deletar os itens duplicados");

        assertAll(new ProfessorExecutable(results.iterator().next(), massa));
    }

    @DisplayName("FindByFilter utilizando um filtro com o salario do Professor")
    @ParameterizedTest(name = "Pesquisa do Professor a partir do salario [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaProfessor.class)
    void validarFiltroSalarioProfessor(MassaProfessor massa) {
        ProfessorDTO filter = new ProfessorDTO();
        filter.setSalario(massa.getSalario());

        // Obtem as informacoes do banco de dados
        Collection<Professor> results = TesteProfessorQueries.bc.findByFilter(filter);
        assertEquals(1, results.size(), "O metodo findByFilter deveria ter retornado apenas 1 resultado, favor deletar os itens duplicados");

        assertAll(new ProfessorExecutable(results.iterator().next(), massa));
    }

    @DisplayName("FindByFilter utilizando um filtro com o telefone do Professor")
    @ParameterizedTest(name = "Pesquisa do Professor a partir do telefone [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaProfessor.class)
    void validarFiltroTelefoneProfessor(MassaProfessor massa) {
        ProfessorDTO filter = new ProfessorDTO();
        filter.setTelefone(massa.getTelefone());

        // Obtem as informacoes do banco de dados
        Collection<Professor> results = TesteProfessorQueries.bc.findByFilter(filter);
        assertEquals(1, results.size(), "O metodo findByFilter deveria ter retornado apenas 1 resultado, favor deletar os itens duplicados");

        assertAll(new ProfessorExecutable(results.iterator().next(), massa));
    }

    @DisplayName("FindByFilter utilizando um filtro com a cidade da instituicao")
    @ParameterizedTest(name = "Pesquisa do Professor a partir da cidade da instituicao [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaProfessor.class)
    void validarFiltroCidadeInstituicao(MassaProfessor massa) {
        ProfessorDTO filter = new ProfessorDTO();
        filter.setCidade(massa.getInstituicao().getCidade());

        // Obtem as informacoes do banco de dados
        Collection<Professor> results = TesteProfessorQueries.bc.findByFilter(filter);
        assertEquals(1, results.size(), "O metodo findByFilter deveria ter retornado apenas 1 resultado, favor deletar os itens duplicados");

        assertAll(new ProfessorExecutable(results.iterator().next(), massa));
    }

    @DisplayName("FindByFilter utilizando um filtro com o estado da instituicao")
    @ParameterizedTest(name = "Pesquisa do Professor a partir do estado da instituicao [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaProfessor.class)
    void validarFiltroEstadoInstituicao(MassaProfessor massa) {
        ProfessorDTO filter = new ProfessorDTO();
        filter.setEstado(massa.getInstituicao().getEstado());

        // Obtem as informacoes do banco de dados
        Collection<Professor> results = TesteProfessorQueries.bc.findByFilter(filter);
        assertEquals(6, results.size(), "O metodo findByFilter deveria ter retornado 6 resultados, favor deletar os itens duplicados");
    }

    @DisplayName("FindByFilter utilizando um filtro com o nome da instituicao")
    @ParameterizedTest(name = "Pesquisa do Professor a partir do nome da instituicao [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaProfessor.class)
    void validarFiltroNomeInstituicao(MassaProfessor massa) {
        ProfessorDTO filter = new ProfessorDTO();
        filter.setNomeInstituicao(massa.getInstituicao().getNome());

        // Obtem as informacoes do banco de dados
        Collection<Professor> results = TesteProfessorQueries.bc.findByFilter(filter);
        assertEquals(1, results.size(), "O metodo findByFilter deveria ter retornado apenas 1 resultado, favor deletar os itens duplicados");

        assertAll(new ProfessorExecutable(results.iterator().next(), massa));
    }

    @DisplayName("FindByFilter utilizando um filtro com o pais da instituicao")
    @ParameterizedTest(name = "Pesquisa do Professor a partir do pais da instituicao [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaProfessor.class)
    void validarFiltroPaisInstituicao(MassaProfessor massa) {
        ProfessorDTO filter = new ProfessorDTO();
        filter.setPais(massa.getInstituicao().getPais());

        // Obtem as informacoes do banco de dados
        Collection<Professor> results = TesteProfessorQueries.bc.findByFilter(filter);
        assertEquals(6, results.size(), "O metodo findByFilter deveria ter retornado 6 resultados, favor deletar os itens duplicados");
    }
}