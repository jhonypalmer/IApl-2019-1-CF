package br.ufg.es.iapl.feriados.external.model

class Token {

    public boolean sucess
    public String message
    public Content value

    String getMessage() {
        return message
    }

    void setMessage(String message) {
        this.message = message
    }

    boolean isSucess() {
        return sucess
    }

    void setSucess(boolean sucess) {
        this.sucess = sucess
    }

    Content getValue() {
        return value
    }

    void setValue(Content value) {
        this.value = value
    }
}

