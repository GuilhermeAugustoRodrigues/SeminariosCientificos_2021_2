package br.com.mauda.seminario.cientificos.junit.util;

import java.util.function.Supplier;

public final class MensagensUtils {

    private MensagensUtils() {
    }

    public static Supplier<String> getErrorMessage(String mensagem) {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        String nomeClasse = stack[3].getClassName();
        String nomeMetodo = stack[3].getMethodName();
        int linha = stack[3].getLineNumber();

        return () -> String.format("Mensagem Erro: [%s]. Classe:[%s]. Metodo:[%s]. Linha:[%d] ", mensagem, nomeClasse, nomeMetodo, linha);
    }

}
