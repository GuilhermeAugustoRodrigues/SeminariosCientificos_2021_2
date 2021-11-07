package br.com.mauda.seminario.cientificos.dao;

import br.com.mauda.seminario.cientificos.dto.SeminarioDTO;
import br.com.mauda.seminario.cientificos.dto.util.QueryExecutor;
import br.com.mauda.seminario.cientificos.model.Seminario;
import org.hibernate.Hibernate;

import java.util.Collection;

public class SeminarioDAO extends PatternCrudDAO<Seminario, SeminarioDTO> {
    private static final SeminarioDAO instance = new SeminarioDAO();

    private SeminarioDAO() {
        super(Seminario.class);
    }

    public static SeminarioDAO getInstance() {
        return instance;
    }

    @Override
    public void inicializaLazyObjects(Seminario object) {
        if (object == null) {
            return;
        }
        Hibernate.initialize(object.getAreasCientificas());
        Hibernate.initialize(object.getInscricoes());
        object.getProfessores().forEach(professor -> Hibernate.initialize(professor.getSeminarios()));
    }

    @Override
    public Collection<Seminario> findByFilter(SeminarioDTO filter) {
        QueryExecutor<Seminario, SeminarioDAO> query = new QueryExecutor<>(Seminario.class, this).selectFrom("Seminario");
        if (filter.getId() != null) {
            query.whereEquals("id", filter.getId());
        }
        if (filter.getTitulo() != null) {
            query.whereEquals("titulo", filter.getTitulo());
        }
        if (filter.getDescricao() != null) {
            query.whereEquals("descricao", filter.getDescricao());
        }
        if (filter.getData() != null) {
            query.whereEquals("data", filter.getData());
        }
        if (filter.getMesaRedonda() != null) {
            query.whereEquals("mesaRedonda", filter.getMesaRedonda());
        }
        if (filter.getNomeAreaCientifica() != null) {
            query.join("areasCientificas", "ac");
            query.whereEquals("ac.nome", filter.getNomeAreaCientifica());
        }
        if (filter.getNomeProfessor() != null) {
            query.join("professores", "p");
            query.whereEquals("p.nome", filter.getNomeProfessor());
        }
        return query.queryUsingCriteria();
    }
}
