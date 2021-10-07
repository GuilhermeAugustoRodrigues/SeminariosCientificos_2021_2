package br.com.mauda.seminario.cientificos.bc;

import br.com.mauda.seminario.cientificos.dao.InstituicaoDAO;
import br.com.mauda.seminario.cientificos.model.Instituicao;

public class InstituicaoBC extends PatternCrudBC<Instituicao, InstituicaoDAO> {
    public static InstituicaoBC getInstance() {return instance;}

    private static final InstituicaoBC instance = new InstituicaoBC();

    private InstituicaoBC() {
        this.dao = InstituicaoDAO.getInstance();
    }
}
