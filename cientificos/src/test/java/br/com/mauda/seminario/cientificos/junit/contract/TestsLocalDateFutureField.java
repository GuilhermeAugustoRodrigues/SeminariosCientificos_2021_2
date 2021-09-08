package br.com.mauda.seminario.cientificos.junit.contract;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public interface TestsLocalDateFutureField extends TestsGenericField<LocalDate> {

    @Test
    @DisplayName("Campo preechido com data antes da atual")
    default void validarValorAnteriorDataAtual() {
        this.setValue(LocalDate.now().minusDays(30));
        assertThrows(() -> this.executionMethod(), this.getErrorMessage());
    }
}