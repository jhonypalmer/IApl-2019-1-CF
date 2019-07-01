package br.ufg.es.iapl.feriados.external.model;

import com.google.gson.annotations.SerializedName;

public class Content{
    public String token;
    public String email;
    public String internacionalizacao;
    public String id;
    public String idConta;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInternacionalizacao() {
        return internacionalizacao;
    }

    public void setInternacionalizacao(String internacionalizacao) {
        this.internacionalizacao = internacionalizacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdConta() {
        return idConta;
    }

    public void setIdConta(String idConta) {
        this.idConta = idConta;
    }
}
