package br.com.mauda.seminario.cientificos.junit.converter;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import br.com.mauda.seminario.cientificos.junit.massa.MassaAreaCientifica;
import br.com.mauda.seminario.cientificos.model.AreaCientifica;

/**
 * Classe que faz a conversao de um enum de MassaAreaCientifica para a entity AreaCientifica
 *
 * @author Mauda
 *
 */

public class AreaCientificaConverter implements ArgumentConverter {

    @Override
    public Object convert(Object input, ParameterContext parameterContext) throws ArgumentConversionException {
        if (input instanceof MassaAreaCientifica) {
            return this.create((MassaAreaCientifica) input);
        }
        throw new ArgumentConversionException(input + " nao eh uma massa de area cientifica valida");
    }

    public AreaCientifica createBlank() {
        return new AreaCientifica();
    }

    public AreaCientifica create(MassaAreaCientifica enumm) {
        // Cria a area
        AreaCientifica area = this.createBlank();

        // Atualiza as informacoes de acordo com o enum
        this.update(area, enumm);

        // Retorna a area
        return area;
    }

    public void update(AreaCientifica area, MassaAreaCientifica enumm) {
        area.setNome(enumm.getNome());
    }
}