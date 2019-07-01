package br.ufg.es.iapl.feriados.external.model

import groovy.transform.CompileStatic

@CompileStatic
class AccountsContent {
	String message
	boolean success
	List<Account> value
}
