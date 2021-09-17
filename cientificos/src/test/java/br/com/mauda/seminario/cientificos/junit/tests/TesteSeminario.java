package br.com.mauda.seminario.cientificos.junit.tests;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertAll;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.EnumSource;

import br.com.mauda.seminario.cientificos.bc.SeminarioBC;
import br.com.mauda.seminario.cientificos.junit.converter.SeminarioConverter;
import br.com.mauda.seminario.cientificos.junit.converter.dao.SeminarioDAOConverter;
import br.com.mauda.seminario.cientificos.junit.executable.SeminarioExecutable;
import br.com.mauda.seminario.cientificos.junit.massa.MassaSeminario;
import br.com.mauda.seminario.cientificos.model.Seminario;
import br.com.mauda.seminario.cientificos.util.EnumUtils;

class TesteSeminario {

    protected static SeminarioBC bc;
    protected static SeminarioConverter converter;
    protected Seminario seminario;

    @BeforeAll
    static void beforeAll() {
        bc = SeminarioBC.getInstance();
        converter = new SeminarioConverter();
    }

    @BeforeEach
    void beforeEach() {
        this.seminario = TesteSeminario.converter.create(EnumUtils.getInstanceRandomly(MassaSeminario.class));
    }

    @DisplayName("Criacao de um Seminario")
    @ParameterizedTest(name = "Criacao do Seminario [{arguments}]")
    @EnumSource(MassaSeminario.class)
    void criar(@ConvertWith(SeminarioDAOConverter.class) Seminario object) {
        // Verifica se os atributos estao preenchidos corretamente
        assertAll(new SeminarioExecutable(object));

        // Realiza o insert no banco de dados atraves da Business Controller
        TesteSeminario.bc.insert(object);

        // Verifica se o id eh maior que zero, indicando que foi inserido no banco
        assertTrue(object.getId() > 0, "Insert nao foi realizado corretamente pois o ID do objeto nao foi gerado");

        // Obtem uma nova instancia do BD a partir do ID gerado
        Seminario objectBD = TesteSeminario.bc.findById(object.getId());

        // Realiza as verificacoes entre o objeto em memoria e o obtido do banco
        assertAll(new SeminarioExecutable(object, objectBD));
    }
}