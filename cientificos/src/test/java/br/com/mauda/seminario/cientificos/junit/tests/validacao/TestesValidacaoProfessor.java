package br.com.mauda.seminario.cientificos.junit.tests.validacao;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertThrows;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import br.com.mauda.seminario.cientificos.bc.ProfessorBC;
import br.com.mauda.seminario.cientificos.exception.ObjetoNuloException;
import br.com.mauda.seminario.cientificos.junit.contract.TestsDoublePositiveField;
import br.com.mauda.seminario.cientificos.junit.contract.TestsEmailField;
import br.com.mauda.seminario.cientificos.junit.contract.TestsStringField;
import br.com.mauda.seminario.cientificos.junit.converter.ProfessorConverter;
import br.com.mauda.seminario.cientificos.junit.massa.MassaProfessor;
import br.com.mauda.seminario.cientificos.model.Professor;
import br.com.mauda.seminario.cientificos.util.EnumUtils;

class TestesValidacaoProfessor {

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
        this.professor = TestesValidacaoProfessor.converter.create(EnumUtils.getInstanceRandomly(MassaProfessor.class));
    }

    @Test
    @DisplayName("Criacao de um professor nulo")
    void validarNulo() {
        assertThrows(ObjetoNuloException.class, () -> TestesValidacaoProfessor.bc.insert(null), "ER0003");
    }

    @Nested
    @DisplayName("Testes para o email do Professor")
    class EmailProfessor implements TestsEmailField {

        @Override
        public void setValue(String value) {
            TestesValidacaoProfessor.this.professor.setEmail(value);
        }

        @Override
        public void executionMethod() {
            TestesValidacaoProfessor.bc.insert(TestesValidacaoProfessor.this.professor);
        }

        @Override
        public String getErrorMessage() {
            return "ER0060";
        }
    }

    @Nested
    @DisplayName("Testes para o nome do Professor")
    class NomeProfessor implements TestsStringField {

        @Override
        public void setValue(String value) {
            TestesValidacaoProfessor.this.professor.setNome(value);
        }

        @Override
        public void executionMethod() {
            TestesValidacaoProfessor.bc.insert(TestesValidacaoProfessor.this.professor);
        }

        @Override
        public String getErrorMessage() {
            return "ER0061";
        }
    }

    @Nested
    @DisplayName("Testes para o telefone do Professor")
    class TelefoneProfessor implements TestsStringField {

        @Override
        public void setValue(String value) {
            TestesValidacaoProfessor.this.professor.setTelefone(value);
        }

        @Override
        public void executionMethod() {
            TestesValidacaoProfessor.bc.insert(TestesValidacaoProfessor.this.professor);
        }

        @Override
        public String getErrorMessage() {
            return "ER0062";
        }

        @Override
        public int getMaxSizeField() {
            return 15;
        }
    }

    @Nested
    @DisplayName("Testes para o salario do Professor")
    class SalarioProfessor implements TestsDoublePositiveField {

        @Override
        public void setValue(Double value) {
            TestesValidacaoProfessor.this.professor.setSalario(value);
        }

        @Override
        public void executionMethod() {
            TestesValidacaoProfessor.bc.insert(TestesValidacaoProfessor.this.professor);
        }

        @Override
        public String getErrorMessage() {
            return "ER0063";
        }
    }

    @Nested
    @DisplayName("Testes para a Instituicao dentro do Professor")
    class InstituicaoDoProfessor {

        @Test
        @DisplayName("Criacao de um professor com Instituicao nula")
        void validarNulo() throws IllegalAccessException {
            // Metodo que seta a instituicao como null usando reflections
            FieldUtils.writeDeclaredField(TestesValidacaoProfessor.this.professor, "instituicao", null, true);

            assertThrows(ObjetoNuloException.class, () -> TestesValidacaoProfessor.bc.insert(TestesValidacaoProfessor.this.professor), "ER0003");
        }

        @Nested
        @DisplayName("Testes para a cidade da Instituicao")
        class CidadeInstituicao implements TestsStringField {

            @Override
            public void setValue(String value) {
                TestesValidacaoProfessor.this.professor.getInstituicao().setCidade(value);
            }

            @Override
            public void executionMethod() {
                TestesValidacaoProfessor.bc.insert(TestesValidacaoProfessor.this.professor);
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
                TestesValidacaoProfessor.this.professor.getInstituicao().setEstado(value);
            }

            @Override
            public void executionMethod() {
                TestesValidacaoProfessor.bc.insert(TestesValidacaoProfessor.this.professor);
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
                TestesValidacaoProfessor.this.professor.getInstituicao().setNome(value);
            }

            @Override
            public void executionMethod() {
                TestesValidacaoProfessor.bc.insert(TestesValidacaoProfessor.this.professor);
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
                TestesValidacaoProfessor.this.professor.getInstituicao().setPais(value);
            }

            @Override
            public void executionMethod() {
                TestesValidacaoProfessor.bc.insert(TestesValidacaoProfessor.this.professor);
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
                TestesValidacaoProfessor.this.professor.getInstituicao().setSigla(value);
            }

            @Override
            public void executionMethod() {
                TestesValidacaoProfessor.bc.insert(TestesValidacaoProfessor.this.professor);
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
    @DisplayName("Testes para a lista de seminarios dentro do professor")
    class SeminariosDoProfessor {

        @Test
        @DisplayName("Criacao de um Estudante com a lista de inscricoes nula")
        void validarListaInscricoesNula() throws IllegalAccessException {
            // Metodo que seta a lista de inscricoes como null usando reflections
            FieldUtils.writeDeclaredField(TestesValidacaoProfessor.this.professor, "seminarios", null, true);

            assertThrows(ObjetoNuloException.class, () -> TestesValidacaoProfessor.bc.insert(TestesValidacaoProfessor.this.professor), "ER0003");
        }

        @Test
        @DisplayName("Criacao de um Estudante com uma inscricao nula")
        void validarInscrioesComInscricaoNula() {
            TestesValidacaoProfessor.this.professor.getSeminarios().clear();
            TestesValidacaoProfessor.this.professor.adicionarSeminario(null);
            assertThrows(ObjetoNuloException.class, () -> TestesValidacaoProfessor.bc.insert(TestesValidacaoProfessor.this.professor), "ER0003");
        }
    }
}