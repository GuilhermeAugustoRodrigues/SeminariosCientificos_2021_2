package br.com.mauda.seminario.cientificos.junit.util;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.apache.commons.lang3.StringUtils;
import org.junit.platform.launcher.listeners.TestExecutionSummary.Failure;

public class ErrorUnit {

    private String errorMessage, details;
    private Collection<String> tests = new LinkedHashSet<>();
    private Throwable cause;

    protected ErrorUnit() {
    }

    public void addFailure(Failure fail) {
        this.tests.add(fail.getTestIdentifier().getDisplayName());
        this.details = fail.getTestIdentifier().getSource().toString();
        this.errorMessage = fail.getException().getMessage();
        this.cause = fail.getException().getCause();
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getDetails() {
        return this.details;
    }

    public Throwable getCause() {
        return this.cause;
    }

    public String getTests() {
        return StringUtils.join(this.tests, ",\n\t\t\t ");
    }
}
