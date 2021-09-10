package br.com.mauda.seminario.cientificos.junit.contract;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public interface TestsLocalDateTimeFutureField extends TestsGenericField<LocalDateTime> {

    @Test
    @DisplayName("Campo preechido com data antes da atual")
    default void validarValorAnteriorDataAtual() {
        this.setValue(LocalDateTime.now().minusDays(30));
        assertThrows(() -> this.executionMethod(), this.getErrorMessage());
    }
}