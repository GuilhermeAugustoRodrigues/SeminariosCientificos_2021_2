package br.com.mauda.seminario.cientificos.junit.converter;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import br.com.mauda.seminario.cientificos.junit.massa.MassaSeminario;
import br.com.mauda.seminario.cientificos.model.AreaCientifica;
import br.com.mauda.seminario.cientificos.model.Professor;
import br.com.mauda.seminario.cientificos.model.Seminario;

public class SeminarioConverter implements ArgumentConverter {

    protected AreaCientificaConverter areaCientificaConverter = new AreaCientificaConverter();
    protected ProfessorConverter professorConverter = new ProfessorConverter();

    @Override
    public Object convert(Object input, ParameterContext parameterContext) throws ArgumentConversionException {
        if (input instanceof MassaSeminario) {
            return this.create((MassaSeminario) input);
        }
        throw new ArgumentConversionException(input + " nao eh uma massa de seminario valida");
    }

    public Seminario createBlank() {
        // Cria a AreaCientifica
        AreaCientifica areaCientifica = this.areaCientificaConverter.createBlank();

        // Cria o Professor
        Professor professor = this.professorConverter.createBlank();

        return new Seminario(areaCientifica, professor, 1);
    }

    public Seminario create(MassaSeminario enumm) {
        // Cria a AreaCientifica
        AreaCientifica areaCientifica = this.areaCientificaConverter.create(enumm.getAreaCientifica());

        // Cria o Professor
        Professor professor = this.professorConverter.create(enumm.getProfessor());

        // Cria o Seminario
        Seminario seminario = new Seminario(areaCientifica, professor, enumm.getQtdInscricoes());

        // Atualiza as informacoes de acordo com o enum
        this.update(seminario, enumm);

        return seminario;
    }

    public void update(Seminario seminario, MassaSeminario enumm) {
        seminario.setData(enumm.getData());
        seminario.setDescricao(enumm.getDescricao());
        seminario.setMesaRedonda(enumm.getMesaRedonda());
        seminario.setTitulo(enumm.getTitulo());
    }
}