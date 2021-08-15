package br.com.mauda.seminario.cientificos.junit.executable;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertEquals;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertIsNotBlank;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertNotNull;

import org.junit.jupiter.api.function.Executable;

import br.com.mauda.seminario.cientificos.junit.massa.MassaInstituicao;
import br.com.mauda.seminario.cientificos.model.Instituicao;

public class InstituicaoExecutable implements Executable {

    private Instituicao instituicao;
    private MassaInstituicao instituicaoEnum;

    public InstituicaoExecutable(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public InstituicaoExecutable(Instituicao instituicao, MassaInstituicao enumm) {
        this(instituicao);
        this.instituicaoEnum = enumm;
    }

    public void basicVerification(Instituicao instituicao) throws Throwable {
        assertNotNull(instituicao, "Uma Instituicao nao pode ser nula");
        assertIsNotBlank(instituicao.getCidade(), "A cidade de uma Instituicao nao pode ser nula ou em branco");
        assertIsNotBlank(instituicao.getEstado(), "O estado de uma Instituicao nao pode ser nulo ou em branco");
        assertIsNotBlank(instituicao.getNome(), "O nome de uma Instituicao nao pode ser nulo ou em branco");
        assertIsNotBlank(instituicao.getPais(), "O pais de uma Instituicao nao pode ser nulo ou em branco");
        assertIsNotBlank(instituicao.getSigla(), "A sigla de uma Instituicao nao pode ser nula ou em branco");
    }

    @Override
    public void execute() throws Throwable {
        this.basicVerification(this.instituicao);

        if (this.instituicaoEnum != null) {
            assertEquals(this.instituicaoEnum.getCidade(), this.instituicao.getCidade(), "Cidades das instituicoes nao sao iguais");
            assertEquals(this.instituicaoEnum.getEstado(), this.instituicao.getEstado(), "Estados das instituicoes nao sao iguais");
            assertEquals(this.instituicaoEnum.getNome(), this.instituicao.getNome(), "Nomes das instituicoes nao sao iguais");
            assertEquals(this.instituicaoEnum.getPais(), this.instituicao.getPais(), "Paises das instituicoes nao sao iguais");
            assertEquals(this.instituicaoEnum.getSigla(), this.instituicao.getSigla(), "Siglas das instituicoes nao sao iguais");
            return;
        }
    }
}