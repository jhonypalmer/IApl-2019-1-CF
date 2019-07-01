package br.ufg.es.iapl.feriados.external.model;

public class AccountContent {
    public String message;
    public boolean success;
    public Account value;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Account getValue() {
        return value;
    }

    public void setValue(Account value) {
        this.value = value;
    }
}
