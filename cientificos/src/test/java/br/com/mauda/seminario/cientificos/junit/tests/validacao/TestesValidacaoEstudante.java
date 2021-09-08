package br.com.mauda.seminario.cientificos.junit.tests.validacao;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertThrows;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import br.com.mauda.seminario.cientificos.bc.EstudanteBC;
import br.com.mauda.seminario.cientificos.exception.ObjetoNuloException;
import br.com.mauda.seminario.cientificos.junit.contract.TestsEmailField;
import br.com.mauda.seminario.cientificos.junit.contract.TestsStringField;
import br.com.mauda.seminario.cientificos.junit.converter.EstudanteConverter;
import br.com.mauda.seminario.cientificos.junit.massa.MassaEstudante;
import br.com.mauda.seminario.cientificos.model.Estudante;
import br.com.mauda.seminario.cientificos.util.EnumUtils;

class TestesValidacaoEstudante {

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
        this.estudante = TestesValidacaoEstudante.converter.create(EnumUtils.getInstanceRandomly(MassaEstudante.class));
    }

    @Test
    @DisplayName("Criacao de um estudante nulo")
    void validarNulo() {
        assertThrows(ObjetoNuloException.class, () -> TestesValidacaoEstudante.bc.insert(null), "ER0003");
    }

    @Nested
    @DisplayName("Testes para o email do Estudante")
    class EmailEstudante implements TestsEmailField {

        @Override
        public void setValue(String value) {
            TestesValidacaoEstudante.this.estudante.setEmail(value);
        }

        @Override
        public void executionMethod() {
            TestesValidacaoEstudante.bc.insert(TestesValidacaoEstudante.this.estudante);
        }

        @Override
        public String getErrorMessage() {
            return "ER0030";
        }
    }

    @Nested
    @DisplayName("Testes para o nome do Estudante")
    class NomeEstudante implements TestsStringField {

        @Override
        public void setValue(String value) {
            TestesValidacaoEstudante.this.estudante.setNome(value);
        }

        @Override
        public void executionMethod() {
            TestesValidacaoEstudante.bc.insert(TestesValidacaoEstudante.this.estudante);
        }

        @Override
        public String getErrorMessage() {
            return "ER0031";
        }
    }

    @Nested
    @DisplayName("Testes para o telefone do Estudante")
    class TelefoneEstudante implements TestsStringField {

        @Override
        public void setValue(String value) {
            TestesValidacaoEstudante.this.estudante.setTelefone(value);
        }

        @Override
        public void executionMethod() {
            TestesValidacaoEstudante.bc.insert(TestesValidacaoEstudante.this.estudante);
        }

        @Override
        public String getErrorMessage() {
            return "ER0032";
        }

        @Override
        public int getMaxSizeField() {
            return 15;
        }
    }

    @Nested
    @DisplayName("Testes para a Instituicao dentro do Estudante")
    class InstituicaoDoEstudante {

        @Test
        @DisplayName("Criacao de um estudante com Instituicao nula")
        void validarNulo() throws IllegalAccessException {
            // Metodo que seta a instituicao como null usando reflections
            FieldUtils.writeDeclaredField(TestesValidacaoEstudante.this.estudante, "instituicao", null, true);

            assertThrows(ObjetoNuloException.class, () -> TestesValidacaoEstudante.bc.insert(TestesValidacaoEstudante.this.estudante), "ER0003");
        }

        @Nested
        @DisplayName("Testes para a cidade da Instituicao")
        class CidadeInstituicao implements TestsStringField {

            @Override
            public void setValue(String value) {
                TestesValidacaoEstudante.this.estudante.getInstituicao().setCidade(value);
            }

            @Override
            public void executionMethod() {
                TestesValidacaoEstudante.bc.insert(TestesValidacaoEstudante.this.estudante);
            }

            @Override
            public String getErrorMessage() {
                return "ER0050";
            }
        }

        @Nested
        @DisplayName("Testes para o estado da Instituicao")
        class EstadoInstituicao implements TestsStringField {

            @Override
            public void setValue(String value) {
                TestesValidacaoEstudante.this.estudante.getInstituicao().setEstado(value);
            }

            @Override
            public void executionMethod() {
                TestesValidacaoEstudante.bc.insert(TestesValidacaoEstudante.this.estudante);
            }

            @Override
            public String getErrorMessage() {
                return "ER0051";
            }
        }

        @Nested
        @DisplayName("Testes para o nome da Instituicao")
        class NomeInstituicao implements TestsStringField {

            @Override
            public void setValue(String value) {
                TestesValidacaoEstudante.this.estudante.getInstituicao().setNome(value);
            }

            @Override
            public void executionMethod() {
                TestesValidacaoEstudante.bc.insert(TestesValidacaoEstudante.this.estudante);
            }

            @Override
            public String getErrorMessage() {
                return "ER0052";
            }

            @Override
            public int getMaxSizeField() {
                return 100;
            }
        }

        @Nested
        @DisplayName("Testes para o pais da Instituicao")
        class PaisInstituicao implements TestsStringField {

            @Override
            public void setValue(String value) {
                TestesValidacaoEstudante.this.estudante.getInstituicao().setPais(value);
            }

            @Override
            public void executionMethod() {
                TestesValidacaoEstudante.bc.insert(TestesValidacaoEstudante.this.estudante);
            }

            @Override
            public String getErrorMessage() {
                return "ER0053";
            }
        }

        @Nested
        @DisplayName("Testes para a sigla da Instituicao")
        class SiglaInstituicao implements TestsStringField {

            @Override
            public void setValue(String value) {
                TestesValidacaoEstudante.this.estudante.getInstituicao().setSigla(value);
            }

            @Override
            public void executionMethod() {
                TestesValidacaoEstudante.bc.insert(TestesValidacaoEstudante.this.estudante);
            }

            @Override
            public String getErrorMessage() {
                return "ER0054";
            }

            @Override
            public int getMaxSizeField() {
                return 10;
            }
        }
    }

    @Nested
    @DisplayName("Testes para a lista inscricoes dentro do Estudante")
    class InscricoesDoSeminario {

        @Test
        @DisplayName("Criacao de um Estudante com a lista de inscricoes nula")
        void validarListaInscricoesNula() throws IllegalAccessException {
            // Metodo que seta a lista de inscricoes como null usando reflections
            FieldUtils.writeDeclaredField(TestesValidacaoEstudante.this.estudante, "inscricoes", null, true);

            assertThrows(ObjetoNuloException.class, () -> TestesValidacaoEstudante.bc.insert(TestesValidacaoEstudante.this.estudante), "ER0003");
        }

        @Test
        @DisplayName("Criacao de um Estudante com uma inscricao nula")
        void validarInscrioesComInscricaoNula() {
            TestesValidacaoEstudante.this.estudante.getInscricoes().clear();
            TestesValidacaoEstudante.this.estudante.adicionarInscricao(null);
            assertThrows(ObjetoNuloException.class, () -> TestesValidacaoEstudante.bc.insert(TestesValidacaoEstudante.this.estudante), "ER0003");
        }
    }
}