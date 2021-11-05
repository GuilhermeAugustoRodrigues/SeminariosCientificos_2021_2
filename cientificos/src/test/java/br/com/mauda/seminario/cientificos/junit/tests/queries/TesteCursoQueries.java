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

import br.com.mauda.seminario.cientificos.bc.CursoBC;
import br.com.mauda.seminario.cientificos.dto.CursoDTO;
import br.com.mauda.seminario.cientificos.junit.executable.AreaCientificaExecutable;
import br.com.mauda.seminario.cientificos.junit.executable.CursoExecutable;
import br.com.mauda.seminario.cientificos.junit.massa.MassaCurso;
import br.com.mauda.seminario.cientificos.junit.provider.FindAllSource;
import br.com.mauda.seminario.cientificos.model.Curso;

class TesteCursoQueries {

    protected static CursoBC bc;

    @BeforeAll
    static void beforeAll() {
        bc = CursoBC.getInstance();
    }

    @DisplayName("Pesquisa de um Curso pelos metodos findAll e findById")
    @ParameterizedTest(name = "Pesquisa do Curso [{arguments}] pelos metodos findAll e findById")
    @FindAllSource(value = CursoBC.class)
    void pesquisar(Curso objetoFindAll) {
        // Busca pelo FindById
        Curso objetoFindId = TesteCursoQueries.bc.findById(objetoFindAll.getId());

        // Realiza as verificacoes entre o objeto obtido pelo metodo findAll e o objeto obtido pelo findById
        assertAll(new CursoExecutable(objetoFindAll, objetoFindId));

        CursoDTO filter = new CursoDTO();
        // Seta a informacao do filtro
        filter.setId(objetoFindAll.getId());

        // Obtem as informacoes do banco de dados
        Collection<Curso> objetosFindByFilter = TesteCursoQueries.bc.findByFilter(filter);

        assertEquals(1, objetosFindByFilter.size(), "O metodo findByFilter deveria ter retornado apenas 1 resultado, ao buscar pelo ID.");

        // Verifica se os objetos sao iguais
        assertAll(new CursoExecutable(objetoFindAll, objetosFindByFilter.iterator().next()));
    }

    /**
     * Realiza um teste com o filtro nulo, esperando que ocorram problemas
     */
    @Test
    @DisplayName("FindByFilter utilizando um filtro nulo")
    void validarNulo() {
        assertThrows(() -> TesteCursoQueries.bc.findByFilter(null), "ER0001");
    }

    @Test
    @DisplayName("FindByFilter utilizando um filtro vazio")
    void validarFiltroVazio() {
        assertThrows(() -> TesteCursoQueries.bc.findByFilter(new CursoDTO()), "ER0001");
    }

    @DisplayName("FindByFilter utilizando um filtro com o nome do curso")
    @ParameterizedTest(name = "Pesquisa do Curso a partir do nome do Curso [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaCurso.class)
    void validarFiltroNomeCurso(MassaCurso massa) {
        CursoDTO filter = new CursoDTO();
        filter.setNome(massa.getNome());

        // Obtem as informacoes do banco de dados
        Collection<Curso> results = TesteCursoQueries.bc.findByFilter(filter);
        assertEquals(1, results.size(), "O metodo findByFilter deveria ter retornado apenas 1 resultado, favor deletar os itens duplicados");

        assertAll(new CursoExecutable(results.iterator().next(), massa));
    }

    @DisplayName("FindByFilter utilizando um filtro com o nome da area cientifica")
    @ParameterizedTest(name = "Pesquisa do Curso a partir do nome da Area Cientifica [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaCurso.class)
    void validarFiltroNomeAreaCientifica(MassaCurso massa) {
        CursoDTO filter = new CursoDTO();
        filter.setNomeAreaCientifica(massa.getAreaCientifica().getNome());

        // Obtem as informacoes do banco de dados
        Collection<Curso> results = TesteCursoQueries.bc.findByFilter(filter);
        assertEquals(3, results.size(), "O metodo findByFilter deveria ter retornado 3 resultados, favor deletar os itens duplicados");

        assertAll(new AreaCientificaExecutable(results.iterator().next().getAreaCientifica(), massa.getAreaCientifica()));
    }
}