package br.ufg.es.iapl.feriados.controller;

import br.ufg.es.iapl.feriados.external.BankApiManager;
import br.ufg.es.iapl.feriados.external.model.Account;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("external")
public class BankController {

    BankApiManager bankApiManager = new BankApiManager();

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Account getAccount(@PathVariable String id) {
        return bankApiManager.account(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Account> getAccounts() {
        return bankApiManager.listAllAccounts();
    }
}
