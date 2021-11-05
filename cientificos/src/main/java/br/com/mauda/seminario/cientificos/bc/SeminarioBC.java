package br.com.mauda.seminario.cientificos.bc;

import br.com.mauda.seminario.cientificos.dao.SeminarioDAO;
import br.com.mauda.seminario.cientificos.dto.SeminarioDTO;
import br.com.mauda.seminario.cientificos.model.Seminario;

public class SeminarioBC extends PatternCrudBC<Seminario, SeminarioDTO, SeminarioDAO> {
    public static SeminarioBC getInstance() {return instance;}

    private static final SeminarioBC instance = new SeminarioBC();

    private SeminarioBC() {
        this.dao = SeminarioDAO.getInstance();
    }
}
