package br.com.mauda.seminario.cientificos.junit.converter;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import br.com.mauda.seminario.cientificos.junit.massa.MassaProfessor;
import br.com.mauda.seminario.cientificos.model.Instituicao;
import br.com.mauda.seminario.cientificos.model.Professor;

public class ProfessorConverter implements ArgumentConverter {

    protected InstituicaoConverter instituicaoConverter = new InstituicaoConverter();

    @Override
    public Object convert(Object input, ParameterContext parameterContext) throws ArgumentConversionException {
        if (input instanceof MassaProfessor) {
            return this.create((MassaProfessor) input);
        }
        throw new ArgumentConversionException(input + " nao eh uma massa de professor valida");
    }

    public Professor createBlank() {
        // Cria a instituicao
        Instituicao instituicao = this.instituicaoConverter.createBlank();

        // Cria o Professor
        return new Professor(instituicao);
    }

    public Professor create(MassaProfessor enumm) {
        // Cria a instituicao
        Instituicao instituicao = this.instituicaoConverter.create(enumm.getInstituicao());

        // Cria o Professor
        Professor professor = new Professor(instituicao);

        // Atualiza as informacoes de acordo com o enum
        this.update(professor, enumm);

        return professor;
    }

    public void update(Professor professor, MassaProfessor enumm) {
        professor.setNome(enumm.getNome());
        professor.setEmail(enumm.getEmail());
        professor.setTelefone(enumm.getTelefone());
        professor.setSalario(enumm.getSalario());
    }
}