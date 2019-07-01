package br.ufg.es.iapl.feriados.external.model;

import java.util.List;

public class AccountsContent {
    public String message;
    public boolean success;
    public List<Account> value;

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

    public List<Account> getValue() {
        return value;
    }

    public void setValue(List<Account> value) {
        this.value = value;
    }
}
