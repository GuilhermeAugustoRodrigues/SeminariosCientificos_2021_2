package br.com.mauda.seminario.cientificos.junit.contract;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertThrows;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public interface TestsStringField extends TestsGenericField<String> {

    default int getMaxSizeField() {
        return 50;
    }

    @Override
    @Test
    @DisplayName("Campo com valor nulo")
    default void validarNulo() {
        this.setValue(null);
        assertThrows(() -> this.executionMethod(), this.getErrorMessage());
    }

    @Test
    @DisplayName("Campo preechido com somente espacos em branco")
    default void validarValorComEspacos() {
        this.setValue("     ");
        assertThrows(() -> this.executionMethod(), this.getErrorMessage());
    }

    @Test
    @DisplayName("Campo com numero de caracteres maior que o permitido")
    default void validarValorcomTamanhoExcedido() {
        this.setValue(RandomStringUtils.random(this.getMaxSizeField() + 1));
        assertThrows(() -> this.executionMethod(), this.getErrorMessage());
    }
}