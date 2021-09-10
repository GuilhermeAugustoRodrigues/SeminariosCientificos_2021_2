package br.com.mauda.seminario.cientificos.bc;

import br.com.mauda.seminario.cientificos.model.Curso;

public class CursoBC extends PatternCrudBC<Curso> {
    public static CursoBC getInstance() {
        return new CursoBC();
    }
}
