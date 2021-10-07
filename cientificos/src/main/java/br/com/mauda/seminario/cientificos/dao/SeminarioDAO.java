package br.com.mauda.seminario.cientificos.dao;

import br.com.mauda.seminario.cientificos.model.Seminario;
import org.hibernate.Hibernate;

public class SeminarioDAO extends PatternCrudDAO<Seminario> {
    private static final SeminarioDAO instance = new SeminarioDAO();

    private SeminarioDAO() {
        super(Seminario.class);
    }

    public static SeminarioDAO getInstance() {
        return instance;
    }

    @Override
    public void inicializaLazyObjects(Seminario object) {
        object.getProfessores().forEach(professor -> Hibernate.initialize(professor.getSeminarios()));
        Hibernate.initialize(object.getAreasCientificas());
        Hibernate.initialize(object.getInscricoes());
    }
}
