package br.com.mauda.seminario.cientificos.bc;

import br.com.mauda.seminario.cientificos.dao.EstudanteDAO;
import br.com.mauda.seminario.cientificos.model.Estudante;

public class EstudanteBC extends PatternCrudBC<Estudante, EstudanteDAO> {
    public static EstudanteBC getInstance() {
        return instance;
    }

    private static final EstudanteBC instance = new EstudanteBC();

    private EstudanteBC() {
        this.dao = EstudanteDAO.getInstance();
    }
}
