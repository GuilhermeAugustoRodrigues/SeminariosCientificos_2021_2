package br.com.mauda.seminario.cientificos.junit.converter;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import br.com.mauda.seminario.cientificos.junit.massa.MassaCurso;
import br.com.mauda.seminario.cientificos.model.AreaCientifica;
import br.com.mauda.seminario.cientificos.model.Curso;

public class CursoConverter implements ArgumentConverter {

    protected AreaCientificaConverter areaCientificaConverter = new AreaCientificaConverter();

    @Override
    public Object convert(Object input, ParameterContext parameterContext) throws ArgumentConversionException {
        if (input instanceof MassaCurso) {
            return this.create((MassaCurso) input);
        }
        throw new ArgumentConversionException(input + " nao eh uma massa de curso valida");
    }

    public Curso createBlank() {
        // Cria a Area Cientifica
        AreaCientifica area = this.areaCientificaConverter.createBlank();

        // Cria o Curso
        return new Curso(area);
    }

    public Curso create(MassaCurso enumm) {
        // Cria a Area Cientifica
        AreaCientifica area = this.areaCientificaConverter.create(enumm.getAreaCientifica());

        // Cria o Curso
        Curso curso = new Curso(area);

        // Atualiza as informacoes de acordo com o enum
        this.update(curso, enumm);

        // Retorna o curso
        return curso;
    }

    public void update(Curso curso, MassaCurso enumm) {
        curso.setNome(enumm.getNome());
    }
}