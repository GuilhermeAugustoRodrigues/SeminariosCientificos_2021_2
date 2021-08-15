package br.com.mauda.seminario.cientificos.junit.executable;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertAll;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertEquals;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertIsNotBlank;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertNotNull;

import org.junit.jupiter.api.function.Executable;

import br.com.mauda.seminario.cientificos.junit.massa.MassaEstudante;
import br.com.mauda.seminario.cientificos.model.Estudante;

public class EstudanteExecutable implements Executable {

    private Estudante estudante;
    private MassaEstudante estudanteEnum;

    public EstudanteExecutable(Estudante estudante) {
        this.estudante = estudante;
    }

    public EstudanteExecutable(Estudante estudante, MassaEstudante enumm) {
        this(estudante);
        this.estudanteEnum = enumm;
    }

    public void basicVerification(Estudante estudante) throws Throwable {
        assertNotNull(estudante, "Um Estudante nao pode ser nulo");
        assertNotNull(estudante.getInscricoes(), "É necessário inicializar a lista de inscricoes");

        assertIsNotBlank(estudante.getEmail(), "O email de um Estudante nao pode ser nulo ou em branco");
        assertIsNotBlank(estudante.getNome(), "O nome de um Estudante nao pode ser nulo ou em branco");
        assertIsNotBlank(estudante.getTelefone(), "O telefone de um Estudante nao pode ser nulo ou em branco");

        // Verifica se a instituicao dentro do estudante esta preenchida corretamente
        assertAll(new InstituicaoExecutable(estudante.getInstituicao()));
    }

    @Override
    public void execute() throws Throwable {
        this.basicVerification(this.estudante);

        if (this.estudanteEnum != null) {
            assertEquals(this.estudanteEnum.getEmail(), this.estudante.getEmail(), "Emails dos estudantes nao sao iguais");
            assertEquals(this.estudanteEnum.getNome(), this.estudante.getNome(), "Nomes dos estudantes nao sao iguais");
            assertEquals(this.estudanteEnum.getTelefone(), this.estudante.getTelefone(), "Telefones dos estudantes nao sao iguais");

            assertAll(new InstituicaoExecutable(this.estudante.getInstituicao(), this.estudanteEnum.getInstituicao()));
            return;
        }
    }
}
