package br.com.mauda.seminario.cientificos.dao;

import br.com.mauda.seminario.cientificos.model.Professor;

public class ProfessorDAO extends PatternCrudDAO<Professor> {
    private static final ProfessorDAO instance = new ProfessorDAO();

    private ProfessorDAO() {
        super(Professor.class);
    }

    public static ProfessorDAO getInstance() {
        return instance;
    }

    @Override
    public void inicializaLazyObjects(Professor object) {}
}
