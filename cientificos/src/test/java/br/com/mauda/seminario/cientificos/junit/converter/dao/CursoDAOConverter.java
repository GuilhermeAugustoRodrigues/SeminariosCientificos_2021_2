package br.com.mauda.seminario.cientificos.junit.converter.dao;

import java.util.Collection;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.platform.commons.util.Preconditions;

import br.com.mauda.seminario.cientificos.bc.AreaCientificaBC;
import br.com.mauda.seminario.cientificos.dto.AreaCientificaDTO;
import br.com.mauda.seminario.cientificos.junit.converter.CursoConverter;
import br.com.mauda.seminario.cientificos.junit.massa.MassaCurso;
import br.com.mauda.seminario.cientificos.model.AreaCientifica;
import br.com.mauda.seminario.cientificos.model.Curso;

public class CursoDAOConverter implements ArgumentConverter {

    protected static AreaCientificaBC areaCientificaBC = AreaCientificaBC.getInstance();
    protected CursoConverter converter = new CursoConverter();

    @Override
    public Object convert(Object input, ParameterContext parameterContext) throws ArgumentConversionException {
        if (input instanceof MassaCurso) {
            MassaCurso massaCurso = (MassaCurso) input;

            // Inicializa o filtro com o nome da area cientifica para pesquisar no banco de dados
            AreaCientificaDTO filtro = new AreaCientificaDTO();
            filtro.setNome(massaCurso.getAreaCientifica().getNome());

            // Obtem a area cientifica do banco de dados
            Collection<AreaCientifica> areas = CursoDAOConverter.areaCientificaBC.findByFilter(filtro);

            // Verifica se a lista contem elementos
            Preconditions.notNull(areas, "O retorno do metodo findByFilter nao pode ser nulo");
            Preconditions.notEmpty(areas, "O retorno do metodo findByFilter deve conter algum elemento");
            Preconditions.condition(areas.size() == 1,
                "Lista contem mais de uma area cientifica. Favor deletar as areas cientificas duplicadas do banco de dados");

            // Obtem a primeira posicao da Collection
            Curso curso = new Curso(areas.iterator().next());

            // Atualiza as informacoes de acordo com o enum
            this.converter.update(curso, massaCurso);

            // Retorna o curso
            return curso;
        }
        throw new ArgumentConversionException(input + " nao eh uma massa de curso valida");
    }
}