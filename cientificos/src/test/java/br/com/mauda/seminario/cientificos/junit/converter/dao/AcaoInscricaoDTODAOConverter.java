package br.com.mauda.seminario.cientificos.junit.converter.dao;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertAll;

import java.util.Collection;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.platform.commons.util.Preconditions;

import br.com.mauda.seminario.cientificos.bc.EstudanteBC;
import br.com.mauda.seminario.cientificos.bc.InscricaoBC;
import br.com.mauda.seminario.cientificos.dto.EstudanteDTO;
import br.com.mauda.seminario.cientificos.dto.InscricaoDTO;
import br.com.mauda.seminario.cientificos.junit.dto.AcaoInscricaoDTO;
import br.com.mauda.seminario.cientificos.junit.executable.EstudanteExecutable;
import br.com.mauda.seminario.cientificos.junit.executable.InscricaoExecutable;
import br.com.mauda.seminario.cientificos.junit.executable.SeminarioExecutable;
import br.com.mauda.seminario.cientificos.junit.massa.MassaInscricao;
import br.com.mauda.seminario.cientificos.junit.massa.MassaInscricaoCancelarCompra;
import br.com.mauda.seminario.cientificos.junit.massa.MassaInscricaoCheckIn;
import br.com.mauda.seminario.cientificos.junit.massa.MassaInscricaoComprar;
import br.com.mauda.seminario.cientificos.model.Estudante;
import br.com.mauda.seminario.cientificos.model.Inscricao;
import br.com.mauda.seminario.cientificos.model.Seminario;
import br.com.mauda.seminario.cientificos.model.enums.SituacaoInscricaoEnum;

public class AcaoInscricaoDTODAOConverter implements ArgumentConverter {

    protected static EstudanteBC estudanteBC = EstudanteBC.getInstance();
    protected static InscricaoBC inscricaoBC = InscricaoBC.getInstance();
    private SituacaoInscricaoEnum situacaoAntes;

    @Override
    public Object convert(Object input, ParameterContext parameterContext) throws ArgumentConversionException {
        if (input instanceof MassaInscricaoComprar) {
            return this.create((MassaInscricaoComprar) input);
        }
        if (input instanceof MassaInscricaoCancelarCompra) {
            return this.create((MassaInscricaoCancelarCompra) input);
        }
        if (input instanceof MassaInscricaoCheckIn) {
            return this.create((MassaInscricaoCheckIn) input);
        }
        throw new ArgumentConversionException(input + " nao eh uma massa de inscricao valida");
    }

    public AcaoInscricaoDTO create(MassaInscricaoComprar enumm) {
        this.situacaoAntes = enumm.getSituacaoAntes();
        return this.create(enumm.getInscricao());
    }

    public AcaoInscricaoDTO create(MassaInscricaoCancelarCompra enumm) {
        this.situacaoAntes = enumm.getSituacaoAntes();
        return this.create(enumm.getInscricao());
    }

    public AcaoInscricaoDTO create(MassaInscricaoCheckIn enumm) {
        this.situacaoAntes = enumm.getSituacaoAntes();
        return this.create(enumm.getInscricao());
    }

    public AcaoInscricaoDTO create(MassaInscricao massaInscricao) {
        // Inicializa o filtro com o nome do estudante para pesquisar no banco de dados
        EstudanteDTO filtroEstudante = new EstudanteDTO();
        filtroEstudante.setNome(massaInscricao.getEstudante().getNome());

        // Obtem os estudantes do banco de dados
        Collection<Estudante> estudantes = AcaoInscricaoDTODAOConverter.estudanteBC.findByFilter(filtroEstudante);

        // Verifica se a lista contem elementos
        Preconditions.notNull(estudantes, "O retorno do metodo findByFilter nao pode ser nulo");
        Preconditions.notEmpty(estudantes, "O retorno do metodo findByFilter deve conter algum elemento");
        Preconditions.condition(estudantes.size() == 1,
            "Lista contem mais de um estudante. Favor deletar os estudantes duplicados do banco de dados");

        // Obtem a primeira posicao da Collection
        Estudante estudante = estudantes.iterator().next();

        // Inicializa o filtro com a data e o titulo do seminario para pesquisar no banco de dados, alem da situacao disponivel
        InscricaoDTO filtroInscricao = new InscricaoDTO();
        filtroInscricao.setDataSeminario(massaInscricao.getSeminario().getData());
        filtroInscricao.setTituloSeminario(massaInscricao.getSeminario().getTitulo());
        filtroInscricao.getSituacoes().add(this.situacaoAntes);

        // Obtem os estudantes do banco de dados
        Collection<Inscricao> inscricoes = AcaoInscricaoDTODAOConverter.inscricaoBC.findByFilter(filtroInscricao);

        // Verifica se a lista contem elementos
        Preconditions.notNull(inscricoes, "O retorno do metodo findByFilter nao pode ser nulo");
        Preconditions.notEmpty(inscricoes, "O retorno do metodo findByFilter deve conter algum elemento");

        // Obtem a primeira posicao da Collection
        Inscricao inscricao = inscricoes.iterator().next();

        // Cria o Seminario
        Seminario seminario = inscricao.getSeminario();

        // Obtem o direito ao material
        Boolean direitoMaterial = massaInscricao.isDireitoMaterial();

        AcaoInscricaoDTO dto = new AcaoInscricaoDTO(seminario, estudante, inscricao, direitoMaterial);

        // Verifica se os atributos estao preenchidos
        assertAll(new SeminarioExecutable(dto.getSeminario()));

        // Verifica se os atributos estao preenchidos
        assertAll(new EstudanteExecutable(dto.getEstudante()));

        // Verifica se os atributos estao preenchidos
        assertAll(new InscricaoExecutable(dto.getInscricao()));

        return dto;
    }
}