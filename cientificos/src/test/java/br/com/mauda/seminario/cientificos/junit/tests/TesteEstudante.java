package br.com.mauda.seminario.cientificos.junit.tests;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.EnumSource;

import br.com.mauda.seminario.cientificos.junit.converter.EstudanteConverter;
import br.com.mauda.seminario.cientificos.junit.executable.EstudanteExecutable;
import br.com.mauda.seminario.cientificos.junit.massa.MassaEstudante;
import br.com.mauda.seminario.cientificos.model.Estudante;

class TesteEstudante {

    @DisplayName("Criacao de um Estudante")
    @ParameterizedTest(name = "Criacao do Estudante [{arguments}]")
    @EnumSource(MassaEstudante.class)
    void criar(@ConvertWith(EstudanteConverter.class) Estudante object) {
        // Verifica se os atributos estao preenchidos corretamente
        assertAll(new EstudanteExecutable(object));
    }
}