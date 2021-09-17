package br.com.mauda.seminario.cientificos.junit.converter.dao;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import br.com.mauda.seminario.cientificos.junit.converter.AreaCientificaConverter;
import br.com.mauda.seminario.cientificos.junit.converter.InstituicaoConverter;
import br.com.mauda.seminario.cientificos.junit.converter.ProfessorConverter;
import br.com.mauda.seminario.cientificos.junit.converter.SeminarioConverter;
import br.com.mauda.seminario.cientificos.junit.massa.MassaSeminario;
import br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda;
import br.com.mauda.seminario.cientificos.model.AreaCientifica;
import br.com.mauda.seminario.cientificos.model.Instituicao;
import br.com.mauda.seminario.cientificos.model.Professor;
import br.com.mauda.seminario.cientificos.model.Seminario;

public class SeminarioDAOConverter implements ArgumentConverter {

    protected SeminarioConverter converter = new SeminarioConverter();
    protected AreaCientificaConverter areaConverter = new AreaCientificaConverter();
    protected ProfessorConverter professorConverter = new ProfessorConverter();
    protected InstituicaoConverter instituicaoConverter = new InstituicaoConverter();

    @Override
    public Object convert(Object input, ParameterContext parameterContext) throws ArgumentConversionException {
        if (input instanceof MassaSeminario) {
            MassaSeminario massaSeminario = (MassaSeminario) input;

            // Cria uma Area Cientifica temporaria com ID para facilitar o mapeamento
            AreaCientifica areaCientifica = this.areaConverter.create(massaSeminario.getAreaCientifica());

            // Metodo que seta o id da area Cientifica usando reflections
            try {
                FieldUtils.writeDeclaredField(areaCientifica, "id", massaSeminario.getAreaCientifica().getId(), true);
            } catch (IllegalAccessException e) {
                AssertionsMauda.fail("Erro na hora de atribuir o ID a area Cientifica");
            }

            // Cria uma Instituicao temporaria com ID para facilitar o mapeamento
            Instituicao instituicao = this.instituicaoConverter.create(massaSeminario.getProfessor().getInstituicao());

            // Metodo que seta o id da instituicao usando reflections
            try {
                FieldUtils.writeDeclaredField(instituicao, "id", massaSeminario.getProfessor().getInstituicao().getId(), true);
            } catch (IllegalAccessException e) {
                AssertionsMauda.fail("Erro na hora de atribuir o ID a instituicao");
            }

            Professor professor = new Professor(instituicao);
            this.professorConverter.update(professor, massaSeminario.getProfessor());

            // Metodo que seta o id do professor usando reflections
            try {
                FieldUtils.writeDeclaredField(professor, "id", massaSeminario.getProfessor().getId(), true);
            } catch (IllegalAccessException e) {
                AssertionsMauda.fail("Erro na hora de atribuir o ID ao professor");
            }

            // Obtem a primeira posicao da Collection
            Seminario seminario = new Seminario(areaCientifica, professor, massaSeminario.getQtdInscricoes());

            // Atualiza as informacoes de acordo com o enum
            this.converter.update(seminario, massaSeminario);

            return seminario;
        }
        throw new ArgumentConversionException(input + " nao eh uma massa de seminario valida");
    }
}