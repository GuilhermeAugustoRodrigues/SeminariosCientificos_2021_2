package br.com.mauda.seminario.cientificos.junit.converter;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import br.com.mauda.seminario.cientificos.junit.massa.MassaEstudante;
import br.com.mauda.seminario.cientificos.model.Estudante;
import br.com.mauda.seminario.cientificos.model.Instituicao;

public class EstudanteConverter implements ArgumentConverter {

    protected InstituicaoConverter instituicaoConverter = new InstituicaoConverter();

    @Override
    public Object convert(Object input, ParameterContext parameterContext) throws ArgumentConversionException {
        if (input instanceof MassaEstudante) {
            return this.create((MassaEstudante) input);
        }
        throw new ArgumentConversionException(input + " nao eh uma massa de estudante valida");
    }

    public Estudante createBlank() {
        // Cria a instituicao
        Instituicao instituicao = this.instituicaoConverter.createBlank();

        // Cria o Estudante
        return new Estudante(instituicao);
    }

    public Estudante create(MassaEstudante enumm) {
        // Cria a instituicao
        Instituicao instituicao = this.instituicaoConverter.create(enumm.getInstituicao());

        // Cria o Estudante
        Estudante estudante = new Estudante(instituicao);

        // Atualiza as informacoes de acordo com o enum
        this.update(estudante, enumm);

        // Retorna o estudante
        return estudante;
    }

    public void update(Estudante estudante, MassaEstudante enumm) {
        estudante.setNome(enumm.getNome());
        estudante.setEmail(enumm.getEmail());
        estudante.setTelefone(enumm.getTelefone());
    }
}