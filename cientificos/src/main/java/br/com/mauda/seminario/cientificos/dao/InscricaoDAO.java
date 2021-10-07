package br.com.mauda.seminario.cientificos.dao;

import br.com.mauda.seminario.cientificos.model.Inscricao;

public class InscricaoDAO extends PatternCrudDAO<Inscricao> {
    private static final InscricaoDAO instance = new InscricaoDAO();

    private InscricaoDAO() {
        super(Inscricao.class);
    }

    public static InscricaoDAO getInstance() {
        return instance;
    }

    @Override
    public void inicializaLazyObjects(Inscricao object) {}
}
