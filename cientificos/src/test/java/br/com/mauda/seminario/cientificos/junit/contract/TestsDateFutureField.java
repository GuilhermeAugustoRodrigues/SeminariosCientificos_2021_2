package br.com.mauda.seminario.cientificos.junit.contract;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertThrows;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public interface TestsDateFutureField extends TestsGenericField<Date> {

    @Test
    @DisplayName("Campo preechido com data antes da atual")
    default void validarValorAnteriorDataAtual() {
        this.setValue(DateUtils.addDays(new Date(), -30));
        assertThrows(() -> this.executionMethod(), this.getErrorMessage());
    }
}