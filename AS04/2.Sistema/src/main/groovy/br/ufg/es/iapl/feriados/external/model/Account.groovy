package br.ufg.es.iapl.feriados.external.model

class Account {
    long titular
    long agencia
    long numero
    String senha
    float saldo
    float limite
    boolean ativo
    long id

    long getTitular() {
        return titular
    }

    void setTitular(long titular) {
        this.titular = titular
    }

    long getAgencia() {
        return agencia
    }

    void setAgencia(long agencia) {
        this.agencia = agencia
    }

    long getNumero() {
        return numero
    }

    void setNumero(long numero) {
        this.numero = numero
    }

    String getSenha() {
        return senha
    }

    void setSenha(String senha) {
        this.senha = senha
    }

    float getSaldo() {
        return saldo
    }

    void setSaldo(float saldo) {
        this.saldo = saldo
    }

    float getLimite() {
        return limite
    }

    void setLimite(float limite) {
        this.limite = limite
    }

    boolean isAtivo() {
        return ativo
    }

    void setAtivo(boolean ativo) {
        this.ativo = ativo
    }

    long getId() {
        return id
    }

    void setId(long id) {
        this.id = id
    }
}
