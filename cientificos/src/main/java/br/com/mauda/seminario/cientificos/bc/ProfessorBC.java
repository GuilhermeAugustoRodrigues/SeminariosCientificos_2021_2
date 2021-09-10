package br.com.mauda.seminario.cientificos.bc;

import br.com.mauda.seminario.cientificos.model.Professor;

public class ProfessorBC extends PatternCrudBC<Professor> {
    public static ProfessorBC getInstance() {
        return new ProfessorBC();
    }
}
