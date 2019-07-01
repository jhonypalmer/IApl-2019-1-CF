package br.ufg.es.iapl.feriados.external.model

import groovy.transform.CompileStatic

@CompileStatic
class Account {
	long titular
	long agencia
	long numero
	String senha
	float saldo
	float limite
	boolean ativo
	long id
}
