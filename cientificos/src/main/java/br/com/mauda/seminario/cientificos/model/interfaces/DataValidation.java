package br.com.mauda.seminario.cientificos.model.interfaces;

public interface DataValidation {

    /**
     * Realiza a validacao de um objeto para a insercao ou atualizacao correspondente da classe DAO
     *
     * As validacoes de regras de negocio deverao ser realizadas nesse metodo
     *
     * @param object
     */
    void validateForDataModification();

}
