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

import br.com.mauda.seminario.cientificos.bc.InstituicaoBC;
import br.com.mauda.seminario.cientificos.junit.converter.InstituicaoConverter;
import br.com.mauda.seminario.cientificos.junit.executable.InstituicaoExecutable;
import br.com.mauda.seminario.cientificos.junit.massa.MassaInstituicao;
import br.com.mauda.seminario.cientificos.model.Instituicao;
import br.com.mauda.seminario.cientificos.util.EnumUtils;

class TesteInstituicao {

    protected static InstituicaoBC bc;
    protected static InstituicaoConverter converter;
    protected Instituicao instituicao;

    @BeforeAll
    static void beforeAll() {
        bc = InstituicaoBC.getInstance();
        converter = new InstituicaoConverter();
    }

    @BeforeEach
    void beforeEach() {
        this.instituicao = TesteInstituicao.converter.create(EnumUtils.getInstanceRandomly(MassaInstituicao.class));
    }

    @DisplayName("Criacao de uma Instituicao")
    @ParameterizedTest(name = "Criacao da Instituicao [{arguments}]")
    @EnumSource(MassaInstituicao.class)
    void criar(@ConvertWith(InstituicaoConverter.class) Instituicao object) {
        // Cria o objeto
        assertAll(new InstituicaoExecutable(object));

        // Realiza o insert no banco de dados atraves da Business Controller
        TesteInstituicao.bc.insert(object);

        // Verifica se o id eh maior que zero, indicando que foi inserido no banco
        assertTrue(object.getId() > 0, "Insert nao foi realizado corretamente pois o ID do objeto nao foi gerado");

        // Obtem uma nova instancia do BD a partir do ID gerado
        Instituicao objectBD = TesteInstituicao.bc.findById(object.getId());

        // Realiza as verificacoes entre o objeto em memoria e o obtido do banco
        assertAll(new InstituicaoExecutable(object, objectBD));
    }

    @DisplayName("Atualizacao dos atributos de uma Instituicao")
    @ParameterizedTest(name = "Atualizacao da Instituicao [{arguments}]")
    @EnumSource(MassaInstituicao.class)
    void atualizar(@ConvertWith(InstituicaoConverter.class) Instituicao object) {
        // Cria o objeto
        this.criar(object);

        // Atualiza as informacoes de um objeto
        TesteInstituicao.converter.update(object, EnumUtils.getInstanceRandomly(MassaInstituicao.class));

        // Realiza o update no banco de dados atraves da Business Controller
        TesteInstituicao.bc.update(object);

        // Obtem uma nova instancia do BD a partir do ID gerado
        Instituicao objectBD = TesteInstituicao.bc.findById(object.getId());

        // Realiza as verificacoes entre o objeto em memoria e o obtido do banco
        assertAll(new InstituicaoExecutable(object, objectBD));

        // Realiza o delete no banco de dados atraves da Business Controller para nao deixar o registro
        TesteInstituicao.bc.delete(object);
    }

    @DisplayName("Delecao de uma Instituicao")
    @ParameterizedTest(name = "Delecao da Instituicao [{arguments}]")
    @EnumSource(MassaInstituicao.class)
    void deletar(@ConvertWith(InstituicaoConverter.class) Instituicao object) {
        // Realiza a insercao do objeto no banco de dados
        this.criar(object);

        // Remove o objeto do BD
        TesteInstituicao.bc.delete(object);

        // Obtem o objeto do BD a partir do ID do objeto
        Instituicao objectBD = TesteInstituicao.bc.findById(object.getId());

        // Verifica se o objeto deixou de existir no BD
        assertNull(objectBD, "O objeto deveria estar deletado do banco de dados");
    }
}