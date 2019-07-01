package br.ufg.es.iapl.feriados.external.model;

public class User {
    public String email;
    public String senha;
    public String internacionalizacao;

    public User(String email, String senha, String internacionalizacao) {
        this.email = email;
        this.senha = senha;
        this.internacionalizacao = internacionalizacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getInternacionalizacao() {
        return internacionalizacao;
    }

    public void setInternacionalizacao(String internacionalizacao) {
        this.internacionalizacao = internacionalizacao;
    }
}
