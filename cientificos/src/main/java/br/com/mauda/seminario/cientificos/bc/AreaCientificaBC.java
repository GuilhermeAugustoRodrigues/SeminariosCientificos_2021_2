package br.com.mauda.seminario.cientificos.bc;

import br.com.mauda.seminario.cientificos.dao.AreaCientificaDAO;
import br.com.mauda.seminario.cientificos.dto.AreaCientificaDTO;
import br.com.mauda.seminario.cientificos.model.AreaCientifica;

<<<<<<< HEAD
public class AreaCientificaBC extends PatternCrudBC<AreaCientifica, AreaCientificaDTO, AreaCientificaDAO> {
=======
public class AreaCientificaBC extends PatternCrudBC<AreaCientifica, AreaCientificaDAO> {
>>>>>>> 5df95157ec70f83f255ba27b478038cfd3e0e7fe
    public static AreaCientificaBC getInstance() {
        return instance;
    }

    private static final AreaCientificaBC instance = new AreaCientificaBC();

    private AreaCientificaBC() {
        this.dao = AreaCientificaDAO.getInstance();
    }
}
