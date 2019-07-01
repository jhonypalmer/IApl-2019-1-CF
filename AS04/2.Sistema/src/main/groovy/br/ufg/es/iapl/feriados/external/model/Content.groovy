package br.ufg.es.iapl.feriados.external.model


class Content{
    public String token
    public String email
    public String internacionalizacao
    public String id
    public String idConta

    String getToken() {
        return token
    }

    void setToken(String token) {
        this.token = token
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }

    String getInternacionalizacao() {
        return internacionalizacao
    }

    void setInternacionalizacao(String internacionalizacao) {
        this.internacionalizacao = internacionalizacao
    }

    String getId() {
        return id
    }

    void setId(String id) {
        this.id = id
    }

    String getIdConta() {
        return idConta
    }

    void setIdConta(String idConta) {
        this.idConta = idConta
    }
}
