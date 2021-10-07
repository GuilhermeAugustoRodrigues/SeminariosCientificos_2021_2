package br.com.mauda.seminario.cientificos.bc;

import br.com.mauda.seminario.cientificos.dao.AreaCientificaDAO;
import br.com.mauda.seminario.cientificos.model.AreaCientifica;

public class AreaCientificaBC extends PatternCrudBC<AreaCientifica, AreaCientificaDAO> {
    public static AreaCientificaBC getInstance() {return instance;}

    private static final AreaCientificaBC instance = new AreaCientificaBC();

    private AreaCientificaBC() {
        this.dao = AreaCientificaDAO.getInstance();
    }
}
