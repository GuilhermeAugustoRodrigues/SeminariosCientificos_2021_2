package br.com.mauda.seminario.cientificos.dao;

import br.com.mauda.seminario.cientificos.dto.EstudanteDTO;
import br.com.mauda.seminario.cientificos.dto.util.QueryExecutor;
import br.com.mauda.seminario.cientificos.model.Estudante;
import org.hibernate.Hibernate;

import java.util.Collection;

public class EstudanteDAO extends PatternCrudDAO<Estudante, EstudanteDTO> {
    private static final EstudanteDAO instance = new EstudanteDAO();

    private EstudanteDAO() {
        super(Estudante.class);
    }

    public static EstudanteDAO getInstance() {
        return instance;
    }

    @Override
    public void inicializaLazyObjects(Estudante object) {
        if (object == null) {
            return;
        }
        Hibernate.initialize(object.getInscricoes());
    }

    @Override
    public Collection<Estudante> findByFilter(EstudanteDTO filter) {
        QueryExecutor<Estudante, EstudanteDAO> query = new QueryExecutor<>(Estudante.class, this).selectFrom("Estudante");
        if (filter.getId() != null) {
            query.whereEquals("id", filter.getId());
        }
        if (filter.getNome() != null) {
            query.whereEquals("nome", filter.getNome());
        }
        if (filter.getEmail() != null) {
            query.whereEquals("email", filter.getEmail());
        }
        if (filter.getTelefone() != null) {
            query.whereEquals("telefone", filter.getTelefone());
        }
        if (filter.getNomeInstituicao() != null) {
            query.whereEquals("instituicao.nome", filter.getNomeInstituicao());
        }
        if (filter.getPais() != null) {
            query.whereEquals("instituicao.pais", filter.getPais());
        }
        if (filter.getEstado() != null) {
            query.whereEquals("instituicao.estado", filter.getEstado());
        }
        if (filter.getCidade() != null) {
            query.whereEquals("instituicao.cidade", filter.getCidade());
        }
        return query.queryUsingHQL();
    }
}
