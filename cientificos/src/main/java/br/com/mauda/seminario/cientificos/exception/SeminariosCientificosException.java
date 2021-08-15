package br.com.mauda.seminario.cientificos.exception;

/**
 * Classe de Exception para o projeto de Seminarios Cientificos
 *
 * @author Mauda
 *
 */
 
//Comentario para testes de conflito entre arquivos diferentes 

public class SeminariosCientificosException extends RuntimeException {

    private static final long serialVersionUID = 4928599035264976611L;

    public SeminariosCientificosException(String message) {
        super(message);
    }

    public SeminariosCientificosException(Throwable t) {
        super(t);
    }
}
