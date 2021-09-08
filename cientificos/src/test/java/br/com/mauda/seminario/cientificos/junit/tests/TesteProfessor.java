package br.com.mauda.seminario.cientificos.junit.tests;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertAll;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.EnumSource;

import br.com.mauda.seminario.cientificos.bc.ProfessorBC;
import br.com.mauda.seminario.cientificos.junit.converter.ProfessorConverter;
import br.com.mauda.seminario.cientificos.junit.executable.ProfessorExecutable;
import br.com.mauda.seminario.cientificos.junit.massa.MassaProfessor;
import br.com.mauda.seminario.cientificos.model.Professor;
import br.com.mauda.seminario.cientificos.util.EnumUtils;

class TesteProfessor {

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
        this.professor = TesteProfessor.converter.create(EnumUtils.getInstanceRandomly(MassaProfessor.class));
    }

    @DisplayName("Criacao de um Professor")
    @ParameterizedTest(name = "Criacao do Professor [{arguments}]")
    @EnumSource(MassaProfessor.class)
    void criar(@ConvertWith(ProfessorConverter.class) Professor object) {
        // Verifica se os atributos estao preenchidos corretamente
        assertAll(new ProfessorExecutable(object));
        TesteProfessor.bc.insert(object);
    }
}