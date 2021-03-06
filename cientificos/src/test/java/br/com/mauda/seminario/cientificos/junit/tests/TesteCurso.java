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

import br.com.mauda.seminario.cientificos.bc.CursoBC;
import br.com.mauda.seminario.cientificos.junit.converter.CursoConverter;
import br.com.mauda.seminario.cientificos.junit.converter.dao.CursoDAOConverter;
import br.com.mauda.seminario.cientificos.junit.executable.CursoExecutable;
import br.com.mauda.seminario.cientificos.junit.massa.MassaCurso;
import br.com.mauda.seminario.cientificos.model.Curso;
import br.com.mauda.seminario.cientificos.util.EnumUtils;

class TesteCurso {

    protected static CursoBC bc;
    protected static CursoConverter converter;
    protected Curso curso;

    @BeforeAll
    static void beforeAll() {
        bc = CursoBC.getInstance();
        converter = new CursoConverter();
    }

    @BeforeEach
    void beforeEach() {
        this.curso = TesteCurso.converter.create(EnumUtils.getInstanceRandomly(MassaCurso.class));
    }

    @DisplayName("Criacao de um Curso")
    @ParameterizedTest(name = "Criacao do Curso [{arguments}]")
    @EnumSource(MassaCurso.class)
    void criar(@ConvertWith(CursoDAOConverter.class) Curso object) {
        // Verifica se os atributos estao preenchidos corretamente
        assertAll(new CursoExecutable(object));

        // Realiza o insert no banco de dados atraves da Business Controller
        TesteCurso.bc.insert(object);

        // Verifica se o id eh maior que zero, indicando que foi inserido no banco
        assertTrue(object.getId() > 0, "Insert nao foi realizado corretamente pois o ID do objeto nao foi gerado");

        // Obtem uma nova instancia do BD a partir do ID gerado
        Curso objectBD = TesteCurso.bc.findById(object.getId());

        // Realiza as verificacoes entre o objeto em memoria e o obtido do banco
        assertAll(new CursoExecutable(object, objectBD));
    }

    @DisplayName("Atualizacao dos atributos de um Curso")
    @ParameterizedTest(name = "Atualizacao do Curso [{arguments}]")
    @EnumSource(MassaCurso.class)
    void atualizar(@ConvertWith(CursoDAOConverter.class) Curso object) {
        // Cria o objeto
        this.criar(object);

        // Atualiza as informacoes de um objeto
        TesteCurso.converter.update(object, EnumUtils.getInstanceRandomly(MassaCurso.class));

        // Realiza o update no banco de dados atraves da Business Controller
        TesteCurso.bc.update(object);

        // Obtem uma nova instancia do BD a partir do ID gerado
        Curso objectBD = TesteCurso.bc.findById(object.getId());

        // Realiza as verificacoes entre o objeto em memoria e o obtido do banco
        assertAll(new CursoExecutable(object, objectBD));

        // Realiza o delete no banco de dados atraves da Business Controller para nao deixar o registro
        TesteCurso.bc.delete(object);
    }

    @DisplayName("Delecao de um Curso")
    @ParameterizedTest(name = "Delecao do Curso [{arguments}]")
    @EnumSource(MassaCurso.class)
    void deletar(@ConvertWith(CursoDAOConverter.class) Curso object) {
        // Realiza a insercao do objeto no banco de dados
        this.criar(object);

        // Remove o objeto do BD
        TesteCurso.bc.delete(object);

        // Obtem o objeto do BD a partir do ID do objeto
        Curso objectBD = TesteCurso.bc.findById(object.getId());

        // Verifica se o objeto deixou de existir no BD
        assertNull(objectBD, "O objeto deveria estar deletado do banco de dados");
    }
}