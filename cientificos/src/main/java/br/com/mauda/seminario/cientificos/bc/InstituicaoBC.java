package br.com.mauda.seminario.cientificos.bc;

import br.com.mauda.seminario.cientificos.dao.InstituicaoDAO;
import br.com.mauda.seminario.cientificos.dto.InstituicaoDTO;
import br.com.mauda.seminario.cientificos.model.Instituicao;

<<<<<<< HEAD
public class InstituicaoBC extends PatternCrudBC<Instituicao, InstituicaoDTO, InstituicaoDAO> {
=======
public class InstituicaoBC extends PatternCrudBC<Instituicao, InstituicaoDAO> {
>>>>>>> 5df95157ec70f83f255ba27b478038cfd3e0e7fe
    public static InstituicaoBC getInstance() {
        return instance;
    }

    private static final InstituicaoBC instance = new InstituicaoBC();

    private InstituicaoBC() {
        this.dao = InstituicaoDAO.getInstance();
    }
}
