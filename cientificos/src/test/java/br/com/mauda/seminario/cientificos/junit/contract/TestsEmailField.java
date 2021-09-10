package br.com.mauda.seminario.cientificos.junit.contract;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public interface TestsEmailField extends TestsGenericField<String> {

    default int getMaxSizeField() {
        return 50;
    }

    @Test
    @DisplayName("Campo com numero de caracteres maior que o permitido")
    default void validarNomeExcedido() {
        this.setValue("eu101010101010101010101101010101@instituicao.com.br");
        assertThrows(() -> this.executionMethod(), this.getErrorMessage());
    }

    @Test
    @DisplayName("Email sem arroba")
    default void validarEmailSemArroba() {
        this.setValue("estudanteinstituicao.com.br");
        assertThrows(() -> this.executionMethod(), this.getErrorMessage());
    }

    @Test
    @DisplayName("Email sem ponto")
    default void validarEmailSemPonto() {
        this.setValue("estudante@instituicaocombr");
        assertThrows(() -> this.executionMethod(), this.getErrorMessage());
    }
}