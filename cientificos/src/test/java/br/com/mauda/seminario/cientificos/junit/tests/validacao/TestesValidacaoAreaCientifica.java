package br.com.mauda.seminario.cientificos.junit.tests.validacao;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertThrows;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import br.com.mauda.seminario.cientificos.bc.AreaCientificaBC;
import br.com.mauda.seminario.cientificos.exception.ObjetoNuloException;
import br.com.mauda.seminario.cientificos.junit.contract.TestsStringField;
import br.com.mauda.seminario.cientificos.junit.converter.AreaCientificaConverter;
import br.com.mauda.seminario.cientificos.junit.massa.MassaAreaCientifica;
import br.com.mauda.seminario.cientificos.model.AreaCientifica;
import br.com.mauda.seminario.cientificos.util.EnumUtils;

class TestesValidacaoAreaCientifica {

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
        this.areaCientifica = TestesValidacaoAreaCientifica.converter.create(EnumUtils.getInstanceRandomly(MassaAreaCientifica.class));
    }

    @Test
    @DisplayName("Criacao de uma Area Cientifica nula")
    void validarNulo() {
        assertThrows(ObjetoNuloException.class, () -> TestesValidacaoAreaCientifica.bc.insert(null), "ER0003");
    }

    @Nested
    @DisplayName("Testes para o nome de uma Area Cientifica")
    class NomeAreaCientifica implements TestsStringField {

        @Override
        public void setValue(String value) {
            TestesValidacaoAreaCientifica.this.areaCientifica.setNome(value);
        }

        @Override
        public void executionMethod() {
            TestesValidacaoAreaCientifica.bc.insert(TestesValidacaoAreaCientifica.this.areaCientifica);
        }

        @Override
        public String getErrorMessage() {
            return "ER0010";
        }
    }

    @Nested
    @DisplayName("Testes para a lista cursos dentro da Area Cientifica")
    class CursosDoSeminario {

        @Test
        @DisplayName("Criacao de uma Area Cientifica com a lista de cursos nula")
        void validarListaCursosNula() throws IllegalAccessException {
            // Metodo que seta a lista de cursos como null usando reflections
            FieldUtils.writeDeclaredField(TestesValidacaoAreaCientifica.this.areaCientifica, "cursos", null, true);

            assertThrows(ObjetoNuloException.class, () -> TestesValidacaoAreaCientifica.bc.insert(TestesValidacaoAreaCientifica.this.areaCientifica),
                "ER0003");
        }

        @Test
        @DisplayName("Criacao de uma Area Cientifica com um curso nulo")
        void validarCursosComCursoNulo() {
            TestesValidacaoAreaCientifica.this.areaCientifica.getCursos().clear();
            TestesValidacaoAreaCientifica.this.areaCientifica.adicionarCurso(null);
            assertThrows(ObjetoNuloException.class, () -> TestesValidacaoAreaCientifica.bc.insert(TestesValidacaoAreaCientifica.this.areaCientifica),
                "ER0003");
        }
    }
}