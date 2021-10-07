package br.com.mauda.seminario.cientificos.dao;

import br.com.mauda.seminario.cientificos.model.Instituicao;

public class InstituicaoDAO extends PatternCrudDAO<Instituicao> {
    private static final InstituicaoDAO instance = new InstituicaoDAO();

    private InstituicaoDAO() {
        super(Instituicao.class);
    }

    public static InstituicaoDAO getInstance() {
        return instance;
    }

    @Override
    public void inicializaLazyObjects(Instituicao object) {}
}
