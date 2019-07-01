package br.ufg.es.iapl.feriados.external.model

class AccountContent {
    public String message
    public boolean success
    public Account value

    String getMessage() {
        return message
    }

    void setMessage(String message) {
        this.message = message
    }

    boolean isSuccess() {
        return success
    }

    void setSuccess(boolean success) {
        this.success = success
    }

    Account getValue() {
        return value
    }

    void setValue(Account value) {
        this.value = value
    }
}
