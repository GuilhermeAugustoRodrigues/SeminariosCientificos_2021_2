package br.com.mauda.seminario.cientificos.junit.tests;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertAll;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.EnumSource;

import br.com.mauda.seminario.cientificos.bc.EstudanteBC;
import br.com.mauda.seminario.cientificos.junit.converter.EstudanteConverter;
import br.com.mauda.seminario.cientificos.junit.converter.dao.EstudanteDAOConverter;
import br.com.mauda.seminario.cientificos.junit.executable.EstudanteExecutable;
import br.com.mauda.seminario.cientificos.junit.massa.MassaEstudante;
import br.com.mauda.seminario.cientificos.model.Estudante;
import br.com.mauda.seminario.cientificos.util.EnumUtils;

class TesteEstudante {

    protected static EstudanteBC bc;
    protected static EstudanteConverter converter;
    protected Estudante estudante;

    @BeforeAll
    static void beforeAll() {
        bc = EstudanteBC.getInstance();
        converter = new EstudanteConverter();
    }

    @BeforeEach
    void beforeEach() {
        this.estudante = TesteEstudante.converter.create(EnumUtils.getInstanceRandomly(MassaEstudante.class));
    }

    @DisplayName("Criacao de um Estudante")
    @ParameterizedTest(name = "Criacao do Estudante [{arguments}]")
    @EnumSource(MassaEstudante.class)
    void criar(@ConvertWith(EstudanteDAOConverter.class) Estudante object) {
        // Verifica se os atributos estao preenchidos corretamente
        assertAll(new EstudanteExecutable(object));

        // Realiza o insert no banco de dados atraves da Business Controller
        TesteEstudante.bc.insert(object);

        // Verifica se o id eh maior que zero, indicando que foi inserido no banco
        assertTrue(object.getId() > 0, "Insert nao foi realizado corretamente pois o ID do objeto nao foi gerado");

        // Obtem uma nova instancia do BD a partir do ID gerado
        Estudante objectBD = TesteEstudante.bc.findById(object.getId());

        // Realiza as verificacoes entre o objeto em memoria e o obtido do banco
        assertAll(new EstudanteExecutable(object, objectBD));
    }
}