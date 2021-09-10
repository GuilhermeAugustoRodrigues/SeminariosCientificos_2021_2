package br.com.mauda.seminario.cientificos.junit.tests.validacao;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import br.com.mauda.seminario.cientificos.bc.InstituicaoBC;
import br.com.mauda.seminario.cientificos.exception.ObjetoNuloException;
import br.com.mauda.seminario.cientificos.junit.contract.TestsStringField;
import br.com.mauda.seminario.cientificos.junit.converter.InstituicaoConverter;
import br.com.mauda.seminario.cientificos.junit.massa.MassaInstituicao;
import br.com.mauda.seminario.cientificos.model.Instituicao;
import br.com.mauda.seminario.cientificos.util.EnumUtils;

class TestesValidacaoInstituicao {

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
        this.instituicao = TestesValidacaoInstituicao.converter.create(EnumUtils.getInstanceRandomly(MassaInstituicao.class));
    }

    @Test
    @DisplayName("Criacao de uma Instituicao nula")
    void validarNulo() {
        assertThrows(ObjetoNuloException.class, () -> TestesValidacaoInstituicao.bc.insert(null), "ER0003");
    }

    @Nested
    @DisplayName("Testes para a cidade da Instituicao")
    class CidadeInstituicao implements TestsStringField {

        @Override
        public void setValue(String value) {
            TestesValidacaoInstituicao.this.instituicao.setCidade(value);
        }

        @Override
        public void executionMethod() {
            TestesValidacaoInstituicao.bc.insert(TestesValidacaoInstituicao.this.instituicao);
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
            TestesValidacaoInstituicao.this.instituicao.setEstado(value);
        }

        @Override
        public void executionMethod() {
            TestesValidacaoInstituicao.bc.insert(TestesValidacaoInstituicao.this.instituicao);
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
            TestesValidacaoInstituicao.this.instituicao.setNome(value);
        }

        @Override
        public void executionMethod() {
            TestesValidacaoInstituicao.bc.insert(TestesValidacaoInstituicao.this.instituicao);
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
            TestesValidacaoInstituicao.this.instituicao.setPais(value);
        }

        @Override
        public void executionMethod() {
            TestesValidacaoInstituicao.bc.insert(TestesValidacaoInstituicao.this.instituicao);
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
            TestesValidacaoInstituicao.this.instituicao.setSigla(value);
        }

        @Override
        public void executionMethod() {
            TestesValidacaoInstituicao.bc.insert(TestesValidacaoInstituicao.this.instituicao);
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