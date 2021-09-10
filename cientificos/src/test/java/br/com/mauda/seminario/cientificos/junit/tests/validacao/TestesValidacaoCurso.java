package br.com.mauda.seminario.cientificos.junit.tests.validacao;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertThrows;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import br.com.mauda.seminario.cientificos.bc.CursoBC;
import br.com.mauda.seminario.cientificos.exception.ObjetoNuloException;
import br.com.mauda.seminario.cientificos.junit.contract.TestsStringField;
import br.com.mauda.seminario.cientificos.junit.converter.CursoConverter;
import br.com.mauda.seminario.cientificos.junit.massa.MassaCurso;
import br.com.mauda.seminario.cientificos.model.Curso;
import br.com.mauda.seminario.cientificos.util.EnumUtils;

class TestesValidacaoCurso {

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
        this.curso = TestesValidacaoCurso.converter.create(EnumUtils.getInstanceRandomly(MassaCurso.class));
    }

    @Test
    @DisplayName("Criacao de um curso nulo")
    void validarNulo() {
        assertThrows(ObjetoNuloException.class, () -> TestesValidacaoCurso.bc.insert(null), "ER0003");
    }

    @Nested
    @DisplayName("Testes para o nome do Curso")
    class NomeCurso implements TestsStringField {

        @Override
        public void setValue(String value) {
            TestesValidacaoCurso.this.curso.setNome(value);
        }

        @Override
        public void executionMethod() {
            TestesValidacaoCurso.bc.insert(TestesValidacaoCurso.this.curso);
        }

        @Override
        public String getErrorMessage() {
            return "ER0020";
        }
    }

    @Nested
    @DisplayName("Testes para a Area Cientifica dentro do Curso")
    class AreaCientificaDoCurso {

        @Test
        @DisplayName("Criacao de um curso com area cientifica nula")
        void validarNulo() throws IllegalAccessException {
            // Metodo que seta a area cientifica como null usando reflections
            FieldUtils.writeDeclaredField(TestesValidacaoCurso.this.curso, "areaCientifica", null, true);

            assertThrows(ObjetoNuloException.class, () -> TestesValidacaoCurso.bc.insert(TestesValidacaoCurso.this.curso), "ER0003");
        }

        @Nested
        @DisplayName("Testes para o nome da Area Cientifica")
        class NomeAreaCientifica implements TestsStringField {

            @Override
            public void setValue(String value) {
                TestesValidacaoCurso.this.curso.getAreaCientifica().setNome(value);
            }

            @Override
            public void executionMethod() {
                TestesValidacaoCurso.bc.insert(TestesValidacaoCurso.this.curso);
            }

            @Override
            public String getErrorMessage() {
                return "ER0010";
            }
        }
    }
}