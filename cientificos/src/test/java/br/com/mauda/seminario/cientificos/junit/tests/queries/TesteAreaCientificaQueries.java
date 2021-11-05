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

import br.com.mauda.seminario.cientificos.bc.AreaCientificaBC;
import br.com.mauda.seminario.cientificos.dto.AreaCientificaDTO;
import br.com.mauda.seminario.cientificos.junit.executable.AreaCientificaExecutable;
import br.com.mauda.seminario.cientificos.junit.massa.MassaAreaCientifica;
import br.com.mauda.seminario.cientificos.junit.provider.FindAllSource;
import br.com.mauda.seminario.cientificos.model.AreaCientifica;

class TesteAreaCientificaQueries {

    protected static AreaCientificaBC bc;

    @BeforeAll
    static void beforeAll() {
        bc = AreaCientificaBC.getInstance();
    }

    @DisplayName("Pesquisa de uma Area Cientifica pelos metodos findAll e findById")
    @ParameterizedTest(name = "Pesquisa da Area Cientifica [{arguments}] pelos metodos findAll e findById")
    @FindAllSource(value = AreaCientificaBC.class)
    void pesquisar(AreaCientifica objetoFindAll) {
        // Busca pelo FindById
        AreaCientifica objetoFindId = TesteAreaCientificaQueries.bc.findById(objetoFindAll.getId());

        // Realiza as verificacoes entre o objeto obtido pelo metodo findAll e o objeto obtido pelo findById
        assertAll(new AreaCientificaExecutable(objetoFindAll, objetoFindId));

        AreaCientificaDTO filter = new AreaCientificaDTO();
        // Seta a informacao do filtro
        filter.setId(objetoFindAll.getId());

        // Obtem as informacoes do banco de dados
        Collection<AreaCientifica> objetosFindByFilter = TesteAreaCientificaQueries.bc.findByFilter(filter);

        assertEquals(1, objetosFindByFilter.size(), "O metodo findByFilter deveria ter retornado apenas 1 resultado, ao buscar pelo ID.");

        // Verifica se os objetos sao iguais
        assertAll(new AreaCientificaExecutable(objetoFindAll, objetosFindByFilter.iterator().next()));
    }

    /**
     * Realiza um teste com o filtro nulo, esperando que ocorram problemas
     */
    @Test
    @DisplayName("FindByFilter utilizando um filtro nulo")
    void validarNulo() {
        assertThrows(() -> TesteAreaCientificaQueries.bc.findByFilter(null), "ER0001");
    }

    @Test
    @DisplayName("FindByFilter utilizando um filtro vazio")
    void validarFiltroVazio() {
        assertThrows(() -> TesteAreaCientificaQueries.bc.findByFilter(new AreaCientificaDTO()), "ER0001");
    }

    @DisplayName("FindByFilter utilizando um filtro com o nome da area cientifica")
    @ParameterizedTest(name = "Pesquisa da Area Cientifica a partir do nome [{arguments}] pelo metodo FindByFilter")
    @EnumSource(MassaAreaCientifica.class)
    void validarFiltroNomeArea(MassaAreaCientifica massa) {
        AreaCientificaDTO filter = new AreaCientificaDTO();
        filter.setNome(massa.getNome());

        // Obtem as informacoes do banco de dados
        Collection<AreaCientifica> results = TesteAreaCientificaQueries.bc.findByFilter(filter);
        assertEquals(1, results.size(), "O metodo findByFilter deveria ter retornado apenas 1 resultado, favor deletar os itens duplicados");

        assertAll(new AreaCientificaExecutable(results.iterator().next(), massa));
    }
}