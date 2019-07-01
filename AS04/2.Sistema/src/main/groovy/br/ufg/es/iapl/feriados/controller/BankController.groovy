package br.ufg.es.iapl.feriados.controller

import br.ufg.es.iapl.feriados.external.BankApiManager
import br.ufg.es.iapl.feriados.external.model.Account
import groovy.transform.CompileStatic
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CompileStatic
@RestController
@RequestMapping("external")
class BankController {

	BankApiManager bankApiManager = new BankApiManager()

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	Account getAccount(@PathVariable String id) {
		return bankApiManager.account(id)
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	List<Account> getAccounts() {
		return bankApiManager.listAllAccounts()
	}
}
