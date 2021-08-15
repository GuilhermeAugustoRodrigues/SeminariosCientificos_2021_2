package br.com.mauda.seminario.cientificos.junit.converter;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import br.com.mauda.seminario.cientificos.junit.massa.MassaInstituicao;
import br.com.mauda.seminario.cientificos.model.Instituicao;

public class InstituicaoConverter implements ArgumentConverter {

    @Override
    public Object convert(Object input, ParameterContext parameterContext) throws ArgumentConversionException {
        if (input instanceof MassaInstituicao) {
            return this.create((MassaInstituicao) input);
        }
        throw new ArgumentConversionException(input + " nao eh uma massa de Instituicao valida");
    }

    public Instituicao createBlank() {
        return new Instituicao();
    }

    public Instituicao create(MassaInstituicao enumm) {
        // Cria o instituicao
        Instituicao instituicao = this.createBlank();

        // Atualiza as informacoes de acordo com o enum
        this.update(instituicao, enumm);

        // Retorna o instituicao
        return instituicao;
    }

    public void update(Instituicao instituicao, MassaInstituicao enumm) {
        instituicao.setCidade(enumm.getCidade());
        instituicao.setEstado(enumm.getEstado());
        instituicao.setNome(enumm.getNome());
        instituicao.setPais(enumm.getPais());
        instituicao.setSigla(enumm.getSigla());
    }
}