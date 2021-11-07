package br.com.mauda.seminario.cientificos.dao;

import br.com.mauda.seminario.cientificos.dto.CursoDTO;
import br.com.mauda.seminario.cientificos.dto.util.QueryExecutor;
import br.com.mauda.seminario.cientificos.model.Curso;
import org.hibernate.Hibernate;

import java.util.Collection;

public class CursoDAO extends PatternCrudDAO<Curso, CursoDTO> {
    private static final CursoDAO instance = new CursoDAO();

    private CursoDAO() {
        super(Curso.class);
    }

    public static CursoDAO getInstance() {
        return instance;
    }

    @Override
    public void inicializaLazyObjects(Curso object) {
        if (object == null) {
            return;
        }
        Hibernate.initialize(object.getAreaCientifica().getCursos());
    }

    @Override
    public Collection<Curso> findByFilter(CursoDTO filter) {
        QueryExecutor<Curso, CursoDAO> query = new QueryExecutor<>(Curso.class, this).selectFrom("Curso");

        if (filter.getId() != null) {
            query.whereEquals("id", filter.getId());
        }
        if (filter.getNome() != null) {
            query.whereEquals("nome", filter.getNome());
        }
        query.join("areaCientifica", "ac");
        if (filter.getIdAreaCientifica() != null) {
            query.whereEquals("ac.id", filter.getIdAreaCientifica());
        }
        if (filter.getNomeAreaCientifica() != null) {
            query.whereEquals("ac.nome", filter.getNomeAreaCientifica());
        }

        return query.queryUsingCriteria();
    }
}
