package br.com.mauda.seminario.cientificos.bc;

import br.com.mauda.seminario.cientificos.model.Seminario;

public class SeminarioBC extends PatternCrudBC<Seminario> {
    public static SeminarioBC getInstance() {
        return new SeminarioBC();
    }
}
