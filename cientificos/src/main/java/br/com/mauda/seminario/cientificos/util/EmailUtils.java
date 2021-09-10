package br.com.mauda.seminario.cientificos.util;

/**
 * Classe Utilitaria para emails
 *
 * @author Mauda
 *
 */

public class EmailUtils {

    private EmailUtils() {
        // nada a fazer
    }

    /**
     * Pattern para a validacao de emails
     */
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

}
