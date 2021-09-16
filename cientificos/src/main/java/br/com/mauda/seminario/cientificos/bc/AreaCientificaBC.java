package br.com.mauda.seminario.cientificos.bc;

import br.com.mauda.seminario.cientificos.model.AreaCientifica;

public class AreaCientificaBC extends PatternCrudBC<AreaCientifica> {
    public static AreaCientificaBC getInstance() {return instance;}

    private static final AreaCientificaBC instance = new AreaCientificaBC();

    private AreaCientificaBC() {}
}
