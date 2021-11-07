package br.com.mauda.seminario.cientificos.dao;

import br.com.mauda.seminario.cientificos.dto.AreaCientificaDTO;
import br.com.mauda.seminario.cientificos.dto.util.QueryExecutor;
import br.com.mauda.seminario.cientificos.model.AreaCientifica;
import org.hibernate.Hibernate;

import java.util.Collection;

public class AreaCientificaDAO extends PatternCrudDAO<AreaCientifica, AreaCientificaDTO> {
    private static final AreaCientificaDAO instance = new AreaCientificaDAO();

    private AreaCientificaDAO() {
        super(AreaCientifica.class);
    }

    public static AreaCientificaDAO getInstance() {
        return instance;
    }

    @Override
    public void inicializaLazyObjects(AreaCientifica object) {
        if (object == null) {
            return;
        }
        Hibernate.initialize(object.getCursos());
    }

    @Override
    public Collection<AreaCientifica> findByFilter(AreaCientificaDTO filter) {
        QueryExecutor<AreaCientifica, AreaCientificaDAO> query = new QueryExecutor<>(AreaCientifica.class, this).selectFrom("AreaCientifica");
        if (filter.getId() != null) {
            query.whereEquals("id", filter.getId());
        }
        if (filter.getNome() != null) {
            query.whereEquals("nome", filter.getNome());
        }
        return query.queryUsingHQL();
    }
}
