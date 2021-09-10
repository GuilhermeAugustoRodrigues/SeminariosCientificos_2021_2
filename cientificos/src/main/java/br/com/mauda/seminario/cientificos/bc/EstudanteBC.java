package br.com.mauda.seminario.cientificos.bc;

import br.com.mauda.seminario.cientificos.model.Estudante;

public class EstudanteBC extends PatternCrudBC<Estudante> {
    public static EstudanteBC getInstance() {
        return new EstudanteBC();
    }
}
