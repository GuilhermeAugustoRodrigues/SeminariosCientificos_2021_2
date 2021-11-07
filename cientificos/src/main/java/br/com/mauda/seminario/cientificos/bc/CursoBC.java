package br.com.mauda.seminario.cientificos.bc;

import br.com.mauda.seminario.cientificos.dao.CursoDAO;
import br.com.mauda.seminario.cientificos.dto.CursoDTO;
import br.com.mauda.seminario.cientificos.model.Curso;

public class CursoBC extends PatternCrudBC<Curso, CursoDTO, CursoDAO> {
    public static CursoBC getInstance() {
        return instance;
    }

    private static final CursoBC instance = new CursoBC();

    private CursoBC() {
        this.dao = CursoDAO.getInstance();
    }
}
