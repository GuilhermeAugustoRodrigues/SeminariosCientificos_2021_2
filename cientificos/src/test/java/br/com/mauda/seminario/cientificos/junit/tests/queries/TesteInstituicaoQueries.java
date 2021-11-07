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

import br.com.mauda.seminario.cientificos.bc.InstituicaoBC;
import br.com.mauda.seminario.cientificos.dto.InstituicaoDTO;
import br.com.mauda.seminario.cientificos.junit.executable.InstituicaoExecutable;
import br.com.mauda.seminario.cientificos.junit.massa.MassaInstituicao;
import br.com.mauda.seminario.cientificos.junit.provider.FindAllSource;
import br.com.mauda.seminario.cientificos.model.Instituicao;

class TesteInstituicaoQueries {

    protected static InstituicaoBC bc;

    @BeforeAll
    static void beforeAll() {
        bc = InstituicaoBC.getInstance();
    }

    @DisplayName("Pesquisa de uma Instituicao pelos metodos findAll e findById")
    @ParameterizedTest(name = "Pesquisa da Instituicao [{arguments}] pelos metodos findAll e findById")
    @FindAllSource(value = InstituicaoBC.class)
    void pesquisar(Instituicao objetoFindAll) {
        // Busca pelo FindById
        Instituicao objetoFindId = TesteInstituicaoQueries.bc.findById(objetoFindAll.getId());

        // Realiza as verificacoes entre o objeto obtido pelo metodo findAll e o objeto obtido pelo findById
        assertAll(new InstituicaoExecutable(objetoFindAll, objetoFindId));

        InstituicaoDTO filter = new InstituicaoDTO();
        // Seta a informacao do filtro
        filter.setId(objetoFindAll.getId());

        // Obtem as informacoes do banco de dados
        Collection<Instituicao> objetosFindByFilter = TesteInstituicaoQueries.bc.findByFilter(filter);

        assertEquals(1, objetosFindByFilter.size(), "O metodo findByFilter deveria ter retornado apenas 1 resultado, ao buscar pelo ID.");

        // Verifica se os objetos sao iguais
        assertAll(new InstituicaoExecutable(objetoFindAll, objetosFindByFilter.iterator().next()));
    }

    /**
     * Realiza um teste com o filtro nulo, esperando que ocorram problemas
     */
    @Test
    @DisplayName("FindByFilter utilizando um filtro nulo")
    void validarNulo() {
        assertThrows(() -> TesteInstituicaoQueries.bc.findByFilter(null), "ER0001");
    }

    @Test
    @DisplayName("FindByFilter utilizando um filtro vazio")
    void validarFiltroVazio() {
        assertThrows(() -> TesteInstituicaoQueries.bc.findByFilter(new InstituicaoDTO()), "ER0001");
    }

    @DisplayName("FindByFilter utilizando um filtro com a cidade da instituicao")
    @ParameterizedTest(name = "Pesquisa da Instituicao a partir da cidade [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaInstituicao.class)
    void validarFiltroCidade(MassaInstituicao massa) {
        InstituicaoDTO filter = new InstituicaoDTO();
        filter.setCidade(massa.getCidade());

        // Obtem as informacoes do banco de dados
        Collection<Instituicao> results = TesteInstituicaoQueries.bc.findByFilter(filter);
        assertEquals(1, results.size(), "O metodo findByFilter deveria ter retornado apenas 1 resultado, favor deletar os itens duplicados");

        assertAll(new InstituicaoExecutable(results.iterator().next(), massa));
    }

    @DisplayName("FindByFilter utilizando um filtro com o estado da instituicao")
    @ParameterizedTest(name = "Pesquisa da Instituicao a partir do estado [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaInstituicao.class)
    void validarFiltroEstado(MassaInstituicao massa) {
        InstituicaoDTO filter = new InstituicaoDTO();
        filter.setEstado(massa.getEstado());

        // Obtem as informacoes do banco de dados
        Collection<Instituicao> results = TesteInstituicaoQueries.bc.findByFilter(filter);
        assertEquals(6, results.size(), "O metodo findByFilter deveria ter retornado 6 resultados, favor deletar os itens duplicados");
    }

    @DisplayName("FindByFilter utilizando um filtro com o nome da instituicao")
    @ParameterizedTest(name = "Pesquisa da Instituicao a partir do nome [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaInstituicao.class)
    void validarFiltroNome(MassaInstituicao massa) {
        InstituicaoDTO filter = new InstituicaoDTO();
        filter.setNome(massa.getNome());

        // Obtem as informacoes do banco de dados
        Collection<Instituicao> results = TesteInstituicaoQueries.bc.findByFilter(filter);
        assertEquals(1, results.size(), "O metodo findByFilter deveria ter retornado apenas 1 resultado, favor deletar os itens duplicados");

        assertAll(new InstituicaoExecutable(results.iterator().next(), massa));
    }

    @DisplayName("FindByFilter utilizando um filtro com o pais da instituicao")
    @ParameterizedTest(name = "Pesquisa da Instituicao a partir do pais [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaInstituicao.class)
    void validarFiltroPais(MassaInstituicao massa) {
        InstituicaoDTO filter = new InstituicaoDTO();
        filter.setPais(massa.getPais());

        // Obtem as informacoes do banco de dados
        Collection<Instituicao> results = TesteInstituicaoQueries.bc.findByFilter(filter);
        assertEquals(6, results.size(), "O metodo findByFilter deveria ter retornado 6 resultados, favor deletar os itens duplicados");
    }

    @DisplayName("FindByFilter utilizando um filtro com a sigla da instituicao")
    @ParameterizedTest(name = "Pesquisa da Instituicao a partir da sigla [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaInstituicao.class)
    void validarFiltroSigla(MassaInstituicao massa) {
        InstituicaoDTO filter = new InstituicaoDTO();
        filter.setSigla(massa.getSigla());

        // Obtem as informacoes do banco de dados
        Collection<Instituicao> results = TesteInstituicaoQueries.bc.findByFilter(filter);
        assertEquals(1, results.size(), "O metodo findByFilter deveria ter retornado apenas 1 resultado, favor deletar os itens duplicados");

        assertAll(new InstituicaoExecutable(results.iterator().next(), massa));
    }
}