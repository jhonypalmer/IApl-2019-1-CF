package br.ufg.es.iapl.feriados.external.model

class User {
    public String email
    public String senha
    public String internacionalizacao

    User(String email, String senha, String internacionalizacao) {
        this.email = email
        this.senha = senha
        this.internacionalizacao = internacionalizacao
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }

    String getSenha() {
        return senha
    }

    void setSenha(String senha) {
        this.senha = senha
    }

    String getInternacionalizacao() {
        return internacionalizacao
    }

    void setInternacionalizacao(String internacionalizacao) {
        this.internacionalizacao = internacionalizacao
    }
}
