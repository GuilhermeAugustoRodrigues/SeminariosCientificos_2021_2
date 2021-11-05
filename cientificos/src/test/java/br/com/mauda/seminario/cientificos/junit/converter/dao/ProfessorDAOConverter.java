package br.com.mauda.seminario.cientificos.junit.converter.dao;

import java.util.Collection;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.platform.commons.util.Preconditions;

import br.com.mauda.seminario.cientificos.bc.InstituicaoBC;
import br.com.mauda.seminario.cientificos.dto.InstituicaoDTO;
import br.com.mauda.seminario.cientificos.junit.converter.ProfessorConverter;
import br.com.mauda.seminario.cientificos.junit.massa.MassaProfessor;
import br.com.mauda.seminario.cientificos.model.Instituicao;
import br.com.mauda.seminario.cientificos.model.Professor;

public class ProfessorDAOConverter implements ArgumentConverter {

    protected static InstituicaoBC instituicaoBC = InstituicaoBC.getInstance();
    protected ProfessorConverter converter = new ProfessorConverter();

    @Override
    public Object convert(Object input, ParameterContext parameterContext) throws ArgumentConversionException {
        if (input instanceof MassaProfessor) {
            MassaProfessor massaProfessor = (MassaProfessor) input;

            // Inicializa o filtro com o nome da instituicao para pesquisar no banco de dados
            InstituicaoDTO filtro = new InstituicaoDTO();
            filtro.setNome(massaProfessor.getInstituicao().getNome());

            // Obtem a instituicao do banco de dados
            Collection<Instituicao> instituicoes = ProfessorDAOConverter.instituicaoBC.findByFilter(filtro);

            // Verifica se a lista contem elementos
            Preconditions.notNull(instituicoes, "O retorno do metodo findByFilter nao pode ser nulo");
            Preconditions.notEmpty(instituicoes, "O retorno do metodo findByFilter deve conter algum elemento");
            Preconditions.condition(instituicoes.size() == 1,
                "Lista contem mais de uma instituicao. Favor deletar as instituicoes duplicadas do banco de dados");

            // Obtem a primeira posicao da Collection
            Professor professor = new Professor(instituicoes.iterator().next());

            // Atualiza as informacoes de acordo com o enum
            this.converter.update(professor, massaProfessor);

            // Retorna o curso
            return professor;
        }
        throw new ArgumentConversionException(input + " nao eh uma massa de professor valida");
    }
}