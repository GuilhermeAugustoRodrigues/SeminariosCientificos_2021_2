package br.com.mauda.seminario.cientificos.junit.tests;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertAll;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.EnumSource;

import br.com.mauda.seminario.cientificos.bc.InstituicaoBC;
import br.com.mauda.seminario.cientificos.junit.converter.InstituicaoConverter;
import br.com.mauda.seminario.cientificos.junit.executable.InstituicaoExecutable;
import br.com.mauda.seminario.cientificos.junit.massa.MassaInstituicao;
import br.com.mauda.seminario.cientificos.model.Instituicao;
import br.com.mauda.seminario.cientificos.util.EnumUtils;

class TesteInstituicao {

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
        this.instituicao = TesteInstituicao.converter.create(EnumUtils.getInstanceRandomly(MassaInstituicao.class));
    }

    @DisplayName("Criacao de uma Instituicao")
    @ParameterizedTest(name = "Criacao da Instituicao [{arguments}]")
    @EnumSource(MassaInstituicao.class)
    void criar(@ConvertWith(InstituicaoConverter.class) Instituicao object) {
        // Verifica se os atributos estao preenchidos corretamente
        assertAll(new InstituicaoExecutable(object));
        TesteInstituicao.bc.insert(object);
    }
}