package br.ufg.es.iapl.feriados.external.model

import groovy.transform.CompileStatic

@CompileStatic
class User {
	public String email
	public String senha
	public String internacionalizacao

	User(String email, String senha, String internacionalizacao) {
		this.email = email
		this.senha = senha
		this.internacionalizacao = internacionalizacao
	}
}
