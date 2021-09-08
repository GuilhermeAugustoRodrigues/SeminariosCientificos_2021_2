package br.com.mauda.seminario.cientificos.junit.tests.validacao;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertThrows;

import java.time.LocalDate;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import br.com.mauda.seminario.cientificos.bc.SeminarioBC;
import br.com.mauda.seminario.cientificos.exception.ObjetoNuloException;
import br.com.mauda.seminario.cientificos.junit.contract.TestsGenericField;
import br.com.mauda.seminario.cientificos.junit.contract.TestsIntegerPositiveField;
import br.com.mauda.seminario.cientificos.junit.contract.TestsLocalDateFutureField;
import br.com.mauda.seminario.cientificos.junit.contract.TestsStringField;
import br.com.mauda.seminario.cientificos.junit.converter.SeminarioConverter;
import br.com.mauda.seminario.cientificos.junit.massa.MassaSeminario;
import br.com.mauda.seminario.cientificos.model.Inscricao;
import br.com.mauda.seminario.cientificos.model.Seminario;
import br.com.mauda.seminario.cientificos.util.EnumUtils;

class TestesValidacaoSeminario {

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
        this.seminario = TestesValidacaoSeminario.converter.create(EnumUtils.getInstanceRandomly(MassaSeminario.class));
    }

    @Test
    @DisplayName("Criacao de um seminario nulo")
    void validarNulo() {
        assertThrows(ObjetoNuloException.class, () -> TestesValidacaoSeminario.bc.insert(null), "ER0003");
    }

    @Nested
    @DisplayName("Testes para o titulo do Seminario")
    class TituloSeminario implements TestsStringField {

        @Override
        public void setValue(String value) {
            TestesValidacaoSeminario.this.seminario.setTitulo(value);
        }

        @Override
        public void executionMethod() {
            TestesValidacaoSeminario.bc.insert(TestesValidacaoSeminario.this.seminario);
        }

        @Override
        public String getErrorMessage() {
            return "ER0072";
        }
    }

    @Nested
    @DisplayName("Testes para a descricao do Seminario")
    class DescricaoSeminario implements TestsStringField {

        @Override
        public void setValue(String value) {
            TestesValidacaoSeminario.this.seminario.setDescricao(value);
        }

        @Override
        public void executionMethod() {
            TestesValidacaoSeminario.bc.insert(TestesValidacaoSeminario.this.seminario);
        }

        @Override
        public String getErrorMessage() {
            return "ER0071";
        }

        @Override
        public int getMaxSizeField() {
            return 200;
        }
    }

    @Nested
    @DisplayName("Testes para a data do Seminario")
    class DataSeminario implements TestsLocalDateFutureField {

        @Override
        public void setValue(LocalDate value) {
            TestesValidacaoSeminario.this.seminario.setData(value);
        }

        @Override
        public void executionMethod() {
            TestesValidacaoSeminario.bc.insert(TestesValidacaoSeminario.this.seminario);
        }

        @Override
        public String getErrorMessage() {
            return "ER0070";
        }

    }

    @Nested
    @DisplayName("Testes para a mesa redonda do Seminario")
    class MesaRedondaSeminario implements TestsGenericField<Boolean> {

        @Override
        public void setValue(Boolean value) {
            TestesValidacaoSeminario.this.seminario.setMesaRedonda(value);
        }

        @Override
        public void executionMethod() {
            TestesValidacaoSeminario.bc.insert(TestesValidacaoSeminario.this.seminario);
        }

        @Override
        public String getErrorMessage() {
            return "ER0073";
        }

    }

    @Nested
    @DisplayName("Testes para a quantidade de inscricoes do Seminario")
    class QuantidadeInscricoesSeminario implements TestsIntegerPositiveField {

        @Override
        public void setValue(Integer value) {
            TestesValidacaoSeminario.this.seminario.setQtdInscricoes(value);
        }

        @Override
        public void executionMethod() {
            TestesValidacaoSeminario.bc.insert(TestesValidacaoSeminario.this.seminario);
        }

        @Override
        public String getErrorMessage() {
            return "ER0074";
        }
    }

    @Nested
    @DisplayName("Testes para as Areas Cientificas dentro do Seminario")
    class AreasCientificasDoSeminario {

        @Test
        @DisplayName("Criacao de um seminario com area cientifica nula")
        void validarNulo() throws IllegalAccessException {
            // Metodo que seta as areas cientificas como null usando reflections
            FieldUtils.writeDeclaredField(TestesValidacaoSeminario.this.seminario, "areasCientificas", null, true);

            assertThrows(() -> TestesValidacaoSeminario.bc.insert(TestesValidacaoSeminario.this.seminario), "ER0076");
        }

        @Test
        @DisplayName("Criacao de um seminario sem areas cientificas")
        void validarBranco() {
            TestesValidacaoSeminario.this.seminario.getAreasCientificas().clear();
            assertThrows(() -> TestesValidacaoSeminario.bc.insert(TestesValidacaoSeminario.this.seminario), "ER0076");
        }

        @Test
        @DisplayName("Criacao de um seminario com area cientifica nula")
        void validarAreaNula() {
            TestesValidacaoSeminario.this.seminario.getAreasCientificas().clear();
            TestesValidacaoSeminario.this.seminario.adicionarAreaCientifica(null);
            assertThrows(ObjetoNuloException.class, () -> TestesValidacaoSeminario.bc.insert(TestesValidacaoSeminario.this.seminario), "ER0003");
        }

        @Nested
        @DisplayName("Testes para o nome da Area Cientifica")
        class NomeAreaCientifica implements TestsStringField {

            @Override
            public void setValue(String value) {
                TestesValidacaoSeminario.this.seminario.getAreasCientificas().get(0).setNome(value);
            }

            @Override
            public void executionMethod() {
                TestesValidacaoSeminario.bc.insert(TestesValidacaoSeminario.this.seminario);
            }

            @Override
            public String getErrorMessage() {
                return "ER0010";
            }
        }
    }

    @Nested
    @DisplayName("Testes para os professores dentro do Seminario")
    class ProfessoresDoSeminario {

        @Test
        @DisplayName("Criacao de um seminario com professor nulo")
        void validarNulo() throws IllegalAccessException {
            // Metodo que seta os professores como null usando reflections
            FieldUtils.writeDeclaredField(TestesValidacaoSeminario.this.seminario, "professores", null, true);

            assertThrows(() -> TestesValidacaoSeminario.bc.insert(TestesValidacaoSeminario.this.seminario), "ER0075");
        }

        @Test
        @DisplayName("Criacao de um seminario sem professores")
        void validarBranco() {
            TestesValidacaoSeminario.this.seminario.getProfessores().clear();
            assertThrows(() -> TestesValidacaoSeminario.bc.insert(TestesValidacaoSeminario.this.seminario), "ER0075");
        }

        @Test
        @DisplayName("Criacao de um seminario com professor nulo")
        void validarProfessorNulo() {
            TestesValidacaoSeminario.this.seminario.getProfessores().clear();
            TestesValidacaoSeminario.this.seminario.adicionarProfessor(null);
            assertThrows(ObjetoNuloException.class, () -> TestesValidacaoSeminario.bc.insert(TestesValidacaoSeminario.this.seminario), "ER0003");
        }

        @Nested
        @DisplayName("Testes para o nome do Professor")
        class NomeProfessor implements TestsStringField {

            @Override
            public void setValue(String value) {
                TestesValidacaoSeminario.this.seminario.getProfessores().get(0).setNome(value);
            }

            @Override
            public void executionMethod() {
                TestesValidacaoSeminario.bc.insert(TestesValidacaoSeminario.this.seminario);
            }

            @Override
            public String getErrorMessage() {
                return "ER0061";
            }
        }
    }

    @Nested
    @DisplayName("Testes para a lista inscricoes dentro do Seminario")
    class InscricoesDoSeminario {

        @Test
        @DisplayName("Criacao de um Seminario com a lista de inscricoes nula")
        void validarListaInscricoesNula() throws IllegalAccessException {
            // Metodo que seta a lista de inscricoes como null usando reflections
            FieldUtils.writeDeclaredField(TestesValidacaoSeminario.this.seminario, "inscricoes", null, true);

            assertThrows(ObjetoNuloException.class, () -> TestesValidacaoSeminario.bc.insert(TestesValidacaoSeminario.this.seminario), "ER0003");
        }

        @Test
        @DisplayName("Criacao de um Seminario com uma inscricao nula na lista de inscricoes")
        void validarInscrioesComInscricaoNula() {
            TestesValidacaoSeminario.this.seminario.getInscricoes().clear();
            TestesValidacaoSeminario.this.seminario.adicionarInscricao(null);
            assertThrows(ObjetoNuloException.class, () -> TestesValidacaoSeminario.bc.insert(TestesValidacaoSeminario.this.seminario), "ER0003");
        }

        @Test
        @DisplayName("Criacao de um Seminario com uma inscricao nula na lista de inscricoes")
        void validarInscricaoComSituacaoNula() throws IllegalAccessException {
            Inscricao inscricao = TestesValidacaoSeminario.this.seminario.getInscricoes().get(0);

            // Metodo que seta a lista de inscricoes como null usando reflections
            FieldUtils.writeDeclaredField(inscricao, "situacao", null, true);

            assertThrows(ObjetoNuloException.class, () -> TestesValidacaoSeminario.bc.insert(TestesValidacaoSeminario.this.seminario), "ER0003");
        }
    }
}