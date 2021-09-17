package br.com.mauda.seminario.cientificos.dto;

public interface FilterValidation {

    /**
     * Realiza a validacao de um objeto para a busca de informacoes quando este eh um filtro da tela
     *
     * As validacoes de regras de negocio deverao ser realizadas nesse metodo
     *
     * @param object
     * @return
     */
    boolean validateForFindData();

}
