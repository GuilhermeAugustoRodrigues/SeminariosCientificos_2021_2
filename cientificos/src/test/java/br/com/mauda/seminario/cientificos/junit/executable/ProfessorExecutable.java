package br.com.mauda.seminario.cientificos.junit.executable;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertAll;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertEquals;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertIsNotBlank;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertNotNull;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertTrue;

import org.junit.jupiter.api.function.Executable;

import br.com.mauda.seminario.cientificos.junit.massa.MassaProfessor;
import br.com.mauda.seminario.cientificos.model.Professor;

public class ProfessorExecutable implements Executable {

    private Professor professor, professorBD;
    private MassaProfessor professorEnum;

    public ProfessorExecutable(Professor professor) {
        this.professor = professor;
    }

    public ProfessorExecutable(Professor professor, MassaProfessor enumm) {
        this(professor);
        this.professorEnum = enumm;
    }

    public ProfessorExecutable(Professor professor, Professor professorBD) {
        this(professor);
        this.professorBD = professorBD;
    }

    public void basicVerification(Professor professor) throws Throwable {
        assertNotNull(professor, "Um Professor nao pode ser nulo");
        assertNotNull(professor.getSeminarios(), "É necessário inicializar a lista de seminarios");

        assertIsNotBlank(professor.getEmail(), "O email de um Professor nao pode ser nulo ou em branco");
        assertIsNotBlank(professor.getNome(), "O nome de um Professor nao pode ser nulo ou em branco");
        assertIsNotBlank(professor.getTelefone(), "O telefone de um Professor nao pode ser nulo ou em branco");
        assertNotNull(professor.getSalario(), "O salario de um Professor nao pode ser nulo");

        assertTrue(professor.getSalario() > 0, "O salario de um Professor deve ser maior que zero");

        // Verifica se a instituicao dentro do professor esta preenchida corretamente
        assertAll(new InstituicaoExecutable(professor.getInstituicao()));
    }

    @Override
    public void execute() throws Throwable {
        this.basicVerification(this.professor);

        if (this.professorEnum != null) {
            assertEquals(this.professorEnum.getEmail(), this.professor.getEmail(), "Emails dos professores nao sao iguais");
            assertEquals(this.professorEnum.getNome(), this.professor.getNome(), "Nomes dos professores nao sao iguais");
            assertEquals(this.professorEnum.getTelefone(), this.professor.getTelefone(), "Telefones dos professores nao sao iguais");
            assertEquals(this.professorEnum.getSalario(), this.professor.getSalario(), "Salario dos professores nao sao iguais");

            assertAll(new InstituicaoExecutable(this.professor.getInstituicao(), this.professorEnum.getInstituicao()));
            return;
        }

        if (this.professorBD != null) {
            this.basicVerification(this.professorBD);
            assertEquals(this.professorBD.getEmail(), this.professor.getEmail(), "Emails dos professores nao sao iguais");
            assertEquals(this.professorBD.getId(), this.professor.getId(), "Ids dos professores nao sao iguais");
            assertEquals(this.professorBD.getNome(), this.professor.getNome(), "Nomes dos professores nao sao iguais");
            assertEquals(this.professorBD.getTelefone(), this.professor.getTelefone(), "Telefones dos professores nao sao iguais");
            assertEquals(this.professorBD.getSalario(), this.professor.getSalario(), "Salario dos professores nao sao iguais");

            assertAll(new InstituicaoExecutable(this.professor.getInstituicao(), this.professor.getInstituicao()));
        }
    }
}
