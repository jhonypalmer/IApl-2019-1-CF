package br.ufg.es.iapl.feriados.external.model

class AccountsContent {
    public String message
    public boolean success
    public List<Account> value

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

    List<Account> getValue() {
        return value
    }

    void setValue(List<Account> value) {
        this.value = value
    }
}
