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

import br.com.mauda.seminario.cientificos.bc.EstudanteBC;
import br.com.mauda.seminario.cientificos.dto.EstudanteDTO;
import br.com.mauda.seminario.cientificos.junit.executable.EstudanteExecutable;
import br.com.mauda.seminario.cientificos.junit.massa.MassaEstudante;
import br.com.mauda.seminario.cientificos.junit.provider.FindAllSource;
import br.com.mauda.seminario.cientificos.model.Estudante;

class TesteEstudanteQueries {

    protected static EstudanteBC bc;

    @BeforeAll
    static void beforeAll() {
        bc = EstudanteBC.getInstance();
    }

    @DisplayName("Pesquisa de um Estudante pelos metodos findAll e findById")
    @ParameterizedTest(name = "Pesquisa do Estudante [{arguments}] pelos metodos findAll e findById")
    @FindAllSource(value = EstudanteBC.class)
    void pesquisar(Estudante objetoFindAll) {
        // Busca pelo FindById
        Estudante objetoFindId = TesteEstudanteQueries.bc.findById(objetoFindAll.getId());

        // Realiza as verificacoes entre o objeto obtido pelo metodo findAll e o objeto obtido pelo findById
        assertAll(new EstudanteExecutable(objetoFindAll, objetoFindId));

        EstudanteDTO filter = new EstudanteDTO();
        // Seta a informacao do filtro
        filter.setId(objetoFindAll.getId());

        // Obtem as informacoes do banco de dados
        Collection<Estudante> objetosFindByFilter = TesteEstudanteQueries.bc.findByFilter(filter);

        assertEquals(1, objetosFindByFilter.size(), "O metodo findByFilter deveria ter retornado apenas 1 resultado, ao buscar pelo ID.");

        // Verifica se os objetos sao iguais
        assertAll(new EstudanteExecutable(objetoFindAll, objetosFindByFilter.iterator().next()));
    }

    /**
     * Realiza um teste com o filtro nulo, esperando que ocorram problemas
     */
    @Test
    @DisplayName("FindByFilter utilizando um filtro nulo")
    void validarNulo() {
        assertThrows(() -> TesteEstudanteQueries.bc.findByFilter(null), "ER0001");
    }

    @Test
    @DisplayName("FindByFilter utilizando um filtro vazio")
    void validarFiltroVazio() {
        assertThrows(() -> TesteEstudanteQueries.bc.findByFilter(new EstudanteDTO()), "ER0001");
    }

    @DisplayName("FindByFilter utilizando um filtro com o email do Estudante")
    @ParameterizedTest(name = "Pesquisa do Estudante a partir do email [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaEstudante.class)
    void validarFiltroEmailEstudante(MassaEstudante massa) {
        EstudanteDTO filter = new EstudanteDTO();
        filter.setEmail(massa.getEmail());

        // Obtem as informacoes do banco de dados
        Collection<Estudante> results = TesteEstudanteQueries.bc.findByFilter(filter);
        assertEquals(1, results.size(), "O metodo findByFilter deveria ter retornado apenas 1 resultado, favor deletar os itens duplicados");

        assertAll(new EstudanteExecutable(results.iterator().next(), massa));
    }

    @DisplayName("FindByFilter utilizando um filtro com o nome do Estudante")
    @ParameterizedTest(name = "Pesquisa do Estudante a partir do nome [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaEstudante.class)
    void validarFiltroNomeEstudante(MassaEstudante massa) {
        EstudanteDTO filter = new EstudanteDTO();
        filter.setNome(massa.getNome());

        // Obtem as informacoes do banco de dados
        Collection<Estudante> results = TesteEstudanteQueries.bc.findByFilter(filter);
        assertEquals(1, results.size(), "O metodo findByFilter deveria ter retornado apenas 1 resultado, favor deletar os itens duplicados");

        assertAll(new EstudanteExecutable(results.iterator().next(), massa));
    }

    @DisplayName("FindByFilter utilizando um filtro com o telefone do Estudante")
    @ParameterizedTest(name = "Pesquisa do Estudante a partir do telefone [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaEstudante.class)
    void validarFiltroTelefoneEstudante(MassaEstudante massa) {
        EstudanteDTO filter = new EstudanteDTO();
        filter.setTelefone(massa.getTelefone());

        // Obtem as informacoes do banco de dados
        Collection<Estudante> results = TesteEstudanteQueries.bc.findByFilter(filter);
        assertEquals(1, results.size(), "O metodo findByFilter deveria ter retornado apenas 1 resultado, favor deletar os itens duplicados");

        assertAll(new EstudanteExecutable(results.iterator().next(), massa));
    }

    @DisplayName("FindByFilter utilizando um filtro com a cidade da instituicao")
    @ParameterizedTest(name = "Pesquisa do Estudante a partir da cidade da Instituicao [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaEstudante.class)
    void validarFiltroCidadeInstituicao(MassaEstudante massa) {
        EstudanteDTO filter = new EstudanteDTO();
        filter.setCidade(massa.getInstituicao().getCidade());

        // Obtem as informacoes do banco de dados
        Collection<Estudante> results = TesteEstudanteQueries.bc.findByFilter(filter);
        assertEquals(1, results.size(), "O metodo findByFilter deveria ter retornado apenas 1 resultado, favor deletar os itens duplicados");

        assertAll(new EstudanteExecutable(results.iterator().next(), massa));
    }

    @DisplayName("FindByFilter utilizando um filtro com o estado da instituicao")
    @ParameterizedTest(name = "Pesquisa do Estudante a partir do estado da Instituicao [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaEstudante.class)
    void validarFiltroEstadoInstituicao(MassaEstudante massa) {
        EstudanteDTO filter = new EstudanteDTO();
        filter.setEstado(massa.getInstituicao().getEstado());

        // Obtem as informacoes do banco de dados
        Collection<Estudante> results = TesteEstudanteQueries.bc.findByFilter(filter);
        assertEquals(6, results.size(), "O metodo findByFilter deveria ter retornado 6 resultados, favor deletar os itens duplicados");
    }

    @DisplayName("FindByFilter utilizando um filtro com o nome da instituicao")
    @ParameterizedTest(name = "Pesquisa do Estudante a partir do nome da Instituicao [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaEstudante.class)
    void validarFiltroNomeInstituicao(MassaEstudante massa) {
        EstudanteDTO filter = new EstudanteDTO();
        filter.setNomeInstituicao(massa.getInstituicao().getNome());

        // Obtem as informacoes do banco de dados
        Collection<Estudante> results = TesteEstudanteQueries.bc.findByFilter(filter);
        assertEquals(1, results.size(), "O metodo findByFilter deveria ter retornado apenas 1 resultado, favor deletar os itens duplicados");

        assertAll(new EstudanteExecutable(results.iterator().next(), massa));
    }

    @DisplayName("FindByFilter utilizando um filtro com o pais da instituicao")
    @ParameterizedTest(name = "Pesquisa do Estudante a partir do pais da Instituicao [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaEstudante.class)
    void validarFiltroPaisInstituicao(MassaEstudante massa) {
        EstudanteDTO filter = new EstudanteDTO();
        filter.setPais(massa.getInstituicao().getPais());

        // Obtem as informacoes do banco de dados
        Collection<Estudante> results = TesteEstudanteQueries.bc.findByFilter(filter);
        assertEquals(6, results.size(), "O metodo findByFilter deveria ter retornado 6 resultados, favor deletar os itens duplicados");
    }
}