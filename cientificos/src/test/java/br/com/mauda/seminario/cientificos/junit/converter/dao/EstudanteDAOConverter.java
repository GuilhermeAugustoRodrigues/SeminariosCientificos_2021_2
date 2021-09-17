package br.com.mauda.seminario.cientificos.junit.converter.dao;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import br.com.mauda.seminario.cientificos.junit.converter.EstudanteConverter;
import br.com.mauda.seminario.cientificos.junit.converter.InstituicaoConverter;
import br.com.mauda.seminario.cientificos.junit.massa.MassaEstudante;
import br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda;
import br.com.mauda.seminario.cientificos.model.Estudante;
import br.com.mauda.seminario.cientificos.model.Instituicao;

public class EstudanteDAOConverter implements ArgumentConverter {

    protected EstudanteConverter converter = new EstudanteConverter();
    protected InstituicaoConverter instituicaoConverter = new InstituicaoConverter();

    @Override
    public Object convert(Object input, ParameterContext parameterContext) throws ArgumentConversionException {
        if (input instanceof MassaEstudante) {
            MassaEstudante massaEstudante = (MassaEstudante) input;

            // Cria uma Instituicao temporaria com ID para facilitar o mapeamento
            Instituicao instituicao = this.instituicaoConverter.create(massaEstudante.getInstituicao());

            // Metodo que seta o id da instituicao usando reflections
            try {
                FieldUtils.writeDeclaredField(instituicao, "id", massaEstudante.getInstituicao().getId(), true);
            } catch (IllegalAccessException e) {
                AssertionsMauda.fail("Erro na hora de atribuir o ID a instituicao");
            }

            Estudante estudante = new Estudante(instituicao);

            // Atualiza as informacoes de acordo com o enum
            this.converter.update(estudante, massaEstudante);

            return estudante;
        }
        throw new ArgumentConversionException(input + " nao eh uma massa de estudante valida");
    }
}