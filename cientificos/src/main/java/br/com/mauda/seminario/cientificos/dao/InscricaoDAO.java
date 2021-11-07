package br.com.mauda.seminario.cientificos.dao;

import br.com.mauda.seminario.cientificos.dto.InscricaoDTO;
import br.com.mauda.seminario.cientificos.dto.util.QueryExecutor;
import br.com.mauda.seminario.cientificos.model.Inscricao;
import org.hibernate.Hibernate;

import java.util.Collection;

public class InscricaoDAO extends PatternCrudDAO<Inscricao, InscricaoDTO> {
    private static final InscricaoDAO instance = new InscricaoDAO();

    private InscricaoDAO() {
        super(Inscricao.class);
    }

    public static InscricaoDAO getInstance() {
        return instance;
    }

    @Override
    public void inicializaLazyObjects(Inscricao object) {
        if (object == null) {
            return;
        }
        if (object.getEstudante() != null) {
            Hibernate.initialize(object.getEstudante().getInscricoes());
        }
        if (object.getSeminario() != null) {
            Hibernate.initialize(object.getSeminario().getAreasCientificas());
        }
        if (object.getSeminario() != null) {
            Hibernate.initialize(object.getSeminario().getInscricoes());
        }
        object.getSeminario().getProfessores().forEach(professor -> Hibernate.initialize(professor.getSeminarios()));
    }

    @Override
    public Collection<Inscricao> findByFilter(InscricaoDTO filter) {
        QueryExecutor<Inscricao, InscricaoDAO> query = new QueryExecutor<>(Inscricao.class, this).selectFrom("Inscricao");

        if (filter.getId() != null) {
            query.whereEquals("id", filter.getId());
        }
        if (filter.getSituacoes() != null && !filter.getSituacoes().isEmpty()) {
            query.whereIn("situacao", filter.getSituacoes());
        }
        if (filter.getDireitoMaterial() != null) {
            query.whereEquals("direitoMaterial", filter.getDireitoMaterial());
        }
        query.join("seminario", "s");
        if (filter.getTituloSeminario() != null) {
            query.whereEquals("s.titulo", filter.getTituloSeminario());
        }
        if (filter.getDataSeminario() != null) {
            query.whereEquals("s.data", filter.getDataSeminario());
        }
        if (filter.getNomeEstudante() != null) {
            query.join("estudante", "e");
            query.whereEquals("e.nome", filter.getNomeEstudante());
        }

        return query.queryUsingCriteria();
    }
}
