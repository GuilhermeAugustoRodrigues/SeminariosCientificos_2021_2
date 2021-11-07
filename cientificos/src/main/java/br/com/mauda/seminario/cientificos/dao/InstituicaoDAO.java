package br.com.mauda.seminario.cientificos.dao;

import br.com.mauda.seminario.cientificos.dto.InstituicaoDTO;
import br.com.mauda.seminario.cientificos.dto.util.QueryExecutor;
import br.com.mauda.seminario.cientificos.model.Instituicao;

import java.util.Collection;

public class InstituicaoDAO extends PatternCrudDAO<Instituicao, InstituicaoDTO> {
    private static final InstituicaoDAO instance = new InstituicaoDAO();

    private InstituicaoDAO() {
        super(Instituicao.class);
    }

    public static InstituicaoDAO getInstance() {
        return instance;
    }

    @Override
    public void inicializaLazyObjects(Instituicao object) {
        //NOTHING TO DO
    }

    @Override
    public Collection<Instituicao> findByFilter(InstituicaoDTO filter) {
        QueryExecutor<Instituicao, InstituicaoDAO> query = new QueryExecutor<>(Instituicao.class, this).selectFrom("Instituicao");
        if (filter.getId() != null) {
            query.whereEquals("id", filter.getId());
        }
        if (filter.getNome() != null) {
            query.whereEquals("nome", filter.getNome());
        }
        if (filter.getSigla() != null) {
            query.whereEquals("sigla", filter.getSigla());
        }
        if (filter.getPais() != null) {
            query.whereEquals("pais", filter.getPais());
        }
        if (filter.getEstado() != null) {
            query.whereEquals("estado", filter.getEstado());
        }
        if (filter.getCidade() != null) {
            query.whereEquals("cidade", filter.getCidade());
        }
        return query.queryUsingHQL();
    }
}
