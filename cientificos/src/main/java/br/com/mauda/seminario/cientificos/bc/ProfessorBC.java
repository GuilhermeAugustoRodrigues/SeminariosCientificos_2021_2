package br.com.mauda.seminario.cientificos.bc;

import br.com.mauda.seminario.cientificos.model.Professor;

public class ProfessorBC extends PatternCrudBC<Professor> {
    public static ProfessorBC getInstance() {return instance;}

    private static final ProfessorBC instance = new ProfessorBC();

    private ProfessorBC() {}
}
