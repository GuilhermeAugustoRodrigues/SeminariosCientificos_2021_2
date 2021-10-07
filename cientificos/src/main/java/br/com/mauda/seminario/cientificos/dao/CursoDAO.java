package br.com.mauda.seminario.cientificos.dao;

import br.com.mauda.seminario.cientificos.model.Curso;
import org.hibernate.Hibernate;

public class CursoDAO extends PatternCrudDAO<Curso> {
    private static final CursoDAO instance = new CursoDAO();

    private CursoDAO() {
        super(Curso.class);
    }

    public static CursoDAO getInstance() {
        return instance;
    }

    @Override
    public void inicializaLazyObjects(Curso object) {
        Hibernate.initialize(object.getAreaCientifica().getCursos());
    }
}
