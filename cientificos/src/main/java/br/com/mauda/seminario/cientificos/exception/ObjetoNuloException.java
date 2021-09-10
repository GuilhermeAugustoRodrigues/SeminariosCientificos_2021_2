package br.com.mauda.seminario.cientificos.exception;

public class ObjetoNuloException extends SeminariosCientificosException {
    private static final long serialVersionUID = 4928599035264976610L;

    public ObjetoNuloException() {
        super("ER0003");
    }

    public ObjetoNuloException(Throwable t) {
        super(t);
    }


}
