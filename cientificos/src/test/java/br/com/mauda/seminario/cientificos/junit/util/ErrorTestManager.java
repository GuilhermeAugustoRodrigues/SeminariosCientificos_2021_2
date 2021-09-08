package br.com.mauda.seminario.cientificos.junit.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary.Failure;

public class ErrorTestManager {

    private Map<String, ErrorUnit> errors = new HashMap<>();

    public ErrorTestManager(List<Failure> failure) {
        for (Failure fail : failure) {
            String messageError = fail.getException().getMessage();
            ErrorUnit errorUnit = this.errors.putIfAbsent(messageError, new ErrorUnit());
            if (errorUnit != null) {
                errorUnit.addFailure(fail);
            }
        }
    }

    public void printErrors() {
        for (ErrorUnit errorTest : this.errors.values()) {
            System.out.println("#######################################");
            System.out.println("Falha no(s) Teste(s): \t" + errorTest.getTests());
            System.out.println("Informacoes detalhadas: " + errorTest.getDetails());
            System.out.println("Mensagem de Erro: " + errorTest.getErrorMessage());
            System.out.println("stackTrace: \n" + errorTest.getCause());
            System.out.println("#######################################");
        }
    }

    public void finalReport(SummaryGeneratingListener listener) {
        System.out.println("###################################################");
        System.out.println("Quantidade de Testes executados: \t\t" + listener.getSummary().getTestsStartedCount() / 2);
        System.out.println("Quantidade de Testes executados com falha: \t" + listener.getSummary().getTestsFailedCount() / 2);
        System.out.println("Quantidade de Testes executados com sucesso: \t" + listener.getSummary().getTestsSucceededCount() / 2);
        System.out.println("###################################################");
    }
}