package br.com.mauda.seminario.cientificos.junit.contract;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public interface TestsDoublePositiveField extends TestsGenericField<Double> {

    @Test
    @DisplayName("Campo preechido com valor negativo")
    default void validarValorNegativo() {
        this.setValue(-1d);
        assertThrows(() -> this.executionMethod(), this.getErrorMessage());
    }

    @Test
    @DisplayName("Campo preechido com valor zero")
    default void validarValorZero() {
        this.setValue(0d);
        assertThrows(() -> this.executionMethod(), this.getErrorMessage());
    }
}