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

import br.com.mauda.seminario.cientificos.bc.ProfessorBC;
import br.com.mauda.seminario.cientificos.junit.converter.ProfessorConverter;
import br.com.mauda.seminario.cientificos.junit.converter.dao.ProfessorDAOConverter;
import br.com.mauda.seminario.cientificos.junit.executable.ProfessorExecutable;
import br.com.mauda.seminario.cientificos.junit.massa.MassaProfessor;
import br.com.mauda.seminario.cientificos.model.Professor;
import br.com.mauda.seminario.cientificos.util.EnumUtils;

class TesteProfessor {

    protected static ProfessorBC bc;
    protected static ProfessorConverter converter;
    protected Professor professor;

    @BeforeAll
    static void beforeAll() {
        bc = ProfessorBC.getInstance();
        converter = new ProfessorConverter();
    }

    @BeforeEach
    void beforeEach() {
        this.professor = TesteProfessor.converter.create(EnumUtils.getInstanceRandomly(MassaProfessor.class));
    }

    @DisplayName("Criacao de um Professor")
    @ParameterizedTest(name = "Criacao do Professor [{arguments}]")
    @EnumSource(MassaProfessor.class)
    void criar(@ConvertWith(ProfessorDAOConverter.class) Professor object) {
        // Verifica se os atributos estao preenchidos corretamente
        assertAll(new ProfessorExecutable(object));

        // Realiza o insert no banco de dados atraves da Business Controller
        TesteProfessor.bc.insert(object);

        // Verifica se o id eh maior que zero, indicando que foi inserido no banco
        assertTrue(object.getId() > 0, "Insert nao foi realizado corretamente pois o ID do objeto nao foi gerado");

        // Obtem uma nova instancia do BD a partir do ID gerado
        Professor objectBD = TesteProfessor.bc.findById(object.getId());

        // Realiza as verificacoes entre o objeto em memoria e o obtido do banco
        assertAll(new ProfessorExecutable(object, objectBD));
    }

    @DisplayName("Atualizacao dos atributos de um Professor")
    @ParameterizedTest(name = "Atualizacao do Professor [{arguments}]")
    @EnumSource(MassaProfessor.class)
    void atualizar(@ConvertWith(ProfessorDAOConverter.class) Professor object) {
        // Cria o objeto
        this.criar(object);

        // Atualiza as informacoes de um objeto
        TesteProfessor.converter.update(object, EnumUtils.getInstanceRandomly(MassaProfessor.class));

        // Realiza o update no banco de dados atraves da Business Controller
        TesteProfessor.bc.update(object);

        // Obtem uma nova instancia do BD a partir do ID gerado
        Professor objectBD = TesteProfessor.bc.findById(object.getId());

        // Realiza as verificacoes entre o objeto em memoria e o obtido do banco
        assertAll(new ProfessorExecutable(object, objectBD));

        // Realiza o delete no banco de dados atraves da Business Controller para nao deixar o registro
        TesteProfessor.bc.delete(object);
    }

    @DisplayName("Delecao de um Professor")
    @ParameterizedTest(name = "Delecao do Professor [{arguments}]")
    @EnumSource(MassaProfessor.class)
    void deletar(@ConvertWith(ProfessorDAOConverter.class) Professor object) {
        // Realiza a insercao do objeto no banco de dados
        this.criar(object);

        // Remove o objeto do BD
        TesteProfessor.bc.delete(object);

        // Obtem o objeto do BD a partir do ID do objeto
        Professor objectBD = TesteProfessor.bc.findById(object.getId());

        // Verifica se o objeto deixou de existir no BD
        assertNull(objectBD, "O objeto deveria estar deletado do banco de dados");
    }
}