package br.com.mauda.seminario.cientificos.junit.converter.dao;

import java.util.Collection;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.platform.commons.util.Preconditions;

import br.com.mauda.seminario.cientificos.bc.AreaCientificaBC;
import br.com.mauda.seminario.cientificos.bc.ProfessorBC;
import br.com.mauda.seminario.cientificos.dto.AreaCientificaDTO;
import br.com.mauda.seminario.cientificos.dto.ProfessorDTO;
import br.com.mauda.seminario.cientificos.junit.converter.SeminarioConverter;
import br.com.mauda.seminario.cientificos.junit.massa.MassaSeminario;
import br.com.mauda.seminario.cientificos.model.AreaCientifica;
import br.com.mauda.seminario.cientificos.model.Professor;
import br.com.mauda.seminario.cientificos.model.Seminario;

public class SeminarioDAOConverter implements ArgumentConverter {

    protected static AreaCientificaBC areaCientificaBC = AreaCientificaBC.getInstance();
    protected static ProfessorBC professorBC = ProfessorBC.getInstance();
    protected SeminarioConverter converter = new SeminarioConverter();

    @Override
    public Object convert(Object input, ParameterContext parameterContext) throws ArgumentConversionException {
        if (input instanceof MassaSeminario) {
            MassaSeminario massaSeminario = (MassaSeminario) input;

            // Inicializa o filtro com o nome da area cientifica para pesquisar no banco de dados
            AreaCientificaDTO filtroAC = new AreaCientificaDTO();
            filtroAC.setNome(massaSeminario.getAreaCientifica().getNome());

            // Obtem a area cientifica do banco de dados
            Collection<AreaCientifica> areas = SeminarioDAOConverter.areaCientificaBC.findByFilter(filtroAC);

            // Verifica se a lista contem elementos
            Preconditions.notNull(areas, "O retorno do metodo findByFilter nao pode ser nulo");
            Preconditions.notEmpty(areas, "O retorno do metodo findByFilter deve conter algum elemento");
            Preconditions.condition(areas.size() == 1,
                "Lista contem mais de uma area cientifica. Favor deletar as instituicoes duplicadas do banco de dados");

            // Obtem a primeira posicao da Collection
            AreaCientifica areaCientifica = areas.iterator().next();

            // Inicializa o filtro com o nome da area cientifica para pesquisar no banco de dados
            ProfessorDTO filtroProfessor = new ProfessorDTO();
            filtroProfessor.setNome(massaSeminario.getProfessor().getNome());

            // Obtem os professores do banco de dados
            Collection<Professor> professores = SeminarioDAOConverter.professorBC.findByFilter(filtroProfessor);

            // Verifica se a lista contem elementos
            Preconditions.notNull(professores, "O retorno do metodo findByFilter nao pode ser nulo");
            Preconditions.notEmpty(professores, "O retorno do metodo findByFilter deve conter algum elemento");
            Preconditions.condition(professores.size() == 1,
                "Lista contem mais de um professor. Favor deletar as instituicoes duplicadas do banco de dados");

            // Obtem a primeira posicao da Collection
            Professor professor = professores.iterator().next();

            // Obtem a primeira posicao da Collection
            Seminario seminario = new Seminario(areaCientifica, professor, massaSeminario.getQtdInscricoes());

            // Atualiza as informacoes de acordo com o enum
            this.converter.update(seminario, massaSeminario);

            // Retorna o curso
            return seminario;
        }
        throw new ArgumentConversionException(input + " nao eh uma massa de seminario valida");
    }
}