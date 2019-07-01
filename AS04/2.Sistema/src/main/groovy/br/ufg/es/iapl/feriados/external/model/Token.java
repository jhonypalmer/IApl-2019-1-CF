package br.ufg.es.iapl.feriados.external.model;

public class Token {

    public boolean sucess;
    public String message;
    public Content value;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public Content getValue() {
        return value;
    }

    public void setValue(Content value) {
        this.value = value;
    }
}

