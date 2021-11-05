package br.com.mauda.seminario.cientificos.junit.tests;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertAll;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertNull;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.EnumSource;

import br.com.mauda.seminario.cientificos.bc.AreaCientificaBC;
import br.com.mauda.seminario.cientificos.junit.converter.AreaCientificaConverter;
import br.com.mauda.seminario.cientificos.junit.executable.AreaCientificaExecutable;
import br.com.mauda.seminario.cientificos.junit.massa.MassaAreaCientifica;
import br.com.mauda.seminario.cientificos.model.AreaCientifica;
import br.com.mauda.seminario.cientificos.util.EnumUtils;

class TesteAreaCientifica {

    protected static AreaCientificaBC bc;
    protected static AreaCientificaConverter converter;
    protected AreaCientifica areaCientifica;

    @BeforeAll
    static void beforeAll() {
        bc = AreaCientificaBC.getInstance();
        converter = new AreaCientificaConverter();
    }

    @BeforeEach
    void beforeEach() {
        this.areaCientifica = TesteAreaCientifica.converter.create(EnumUtils.getInstanceRandomly(MassaAreaCientifica.class));
    }

    @DisplayName("Criacao de uma Area Cientifica")
    @ParameterizedTest(name = "Criacao da Area Cientifica [{arguments}]")
    @EnumSource(MassaAreaCientifica.class)
    void criar(@ConvertWith(AreaCientificaConverter.class) AreaCientifica object) {
        // Verifica se os atributos estao preenchidos corretamente
        assertAll(new AreaCientificaExecutable(object));

        // Realiza o insert no banco de dados atraves da Business Controller
        TesteAreaCientifica.bc.insert(object);

        // Verifica se o id eh maior que zero, indicando que foi inserido no banco
        assertTrue(object.getId() > 0, "Insert nao foi realizado corretamente pois o ID do objeto nao foi gerado");

        // Obtem uma nova instancia do BD a partir do ID gerado
        AreaCientifica objectBD = TesteAreaCientifica.bc.findById(object.getId());

        // Realiza as verificacoes entre o objeto em memoria e o obtido do banco
        assertAll(new AreaCientificaExecutable(object, objectBD));
    }

    @DisplayName("Atualizacao dos atributos de uma Area Cientifica")
    @ParameterizedTest(name = "Atualizacao da Area Cientifica [{arguments}]")
    @EnumSource(MassaAreaCientifica.class)
    void atualizar(@ConvertWith(AreaCientificaConverter.class) AreaCientifica object) {
        // Cria o objeto
        this.criar(object);

        // Atualiza as informacoes de um objeto
        TesteAreaCientifica.converter.update(object, EnumUtils.getInstanceRandomly(MassaAreaCientifica.class));

        // Realiza o update no banco de dados atraves da Business Controller
        TesteAreaCientifica.bc.update(object);

        // Obtem uma nova instancia do BD a partir do ID gerado
        AreaCientifica objectBD = TesteAreaCientifica.bc.findById(object.getId());

        // Realiza as verificacoes entre o objeto em memoria e o obtido do banco
        assertAll(new AreaCientificaExecutable(object, objectBD));

        // Realiza o delete no banco de dados atraves da Business Controller para nao deixar o registro
        TesteAreaCientifica.bc.delete(object);
    }

    @DisplayName("Delecao de uma Area Cientifica")
    @ParameterizedTest(name = "Delecao da Area Cientifica [{arguments}]")
    @EnumSource(MassaAreaCientifica.class)
    void deletar(@ConvertWith(AreaCientificaConverter.class) AreaCientifica object) {
        // Realiza a insercao do objeto no banco de dados
        this.criar(object);

        // Remove o objeto do BD
        TesteAreaCientifica.bc.delete(object);

        // Obtem o objeto do BD a partir do ID do objeto
        AreaCientifica objectBD = TesteAreaCientifica.bc.findById(object.getId());

        // Verifica se o objeto deixou de existir no BD
        assertNull(objectBD, "O objeto deveria estar deletado do banco de dados");
    }
}