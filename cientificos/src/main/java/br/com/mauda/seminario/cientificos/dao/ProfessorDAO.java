package br.com.mauda.seminario.cientificos.dao;

import br.com.mauda.seminario.cientificos.dto.ProfessorDTO;
import br.com.mauda.seminario.cientificos.dto.util.QueryExecutor;
import br.com.mauda.seminario.cientificos.model.Estudante;
import br.com.mauda.seminario.cientificos.model.Professor;
import org.hibernate.Hibernate;

import java.util.Collection;

public class ProfessorDAO extends PatternCrudDAO<Professor, ProfessorDTO> {
    private static final ProfessorDAO instance = new ProfessorDAO();

    private ProfessorDAO() {
        super(Professor.class);
    }

    public static ProfessorDAO getInstance() {
        return instance;
    }

    @Override
    public void inicializaLazyObjects(Professor object) {
        if (object == null) {
            return;
        }
        Hibernate.initialize(object.getSeminarios());
        Hibernate.initialize(object.getInstituicao());
    }

    @Override
    public Collection<Professor> findByFilter(ProfessorDTO filter) {
        QueryExecutor<Professor, ProfessorDAO> query = new QueryExecutor<>(Professor.class, this).selectFrom("Professor");
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
        if (filter.getSalario() != null) {
            query.whereEquals("salario", filter.getSalario());
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
