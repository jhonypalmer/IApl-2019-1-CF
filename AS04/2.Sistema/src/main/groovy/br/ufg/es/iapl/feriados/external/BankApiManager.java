package br.ufg.es.iapl.feriados.external;


import br.ufg.es.iapl.feriados.controller.BankController;
import br.ufg.es.iapl.feriados.external.api.Constants;
import br.ufg.es.iapl.feriados.external.model.Account;
import br.ufg.es.iapl.feriados.external.model.AccountContent;
import br.ufg.es.iapl.feriados.external.model.AccountsContent;
import br.ufg.es.iapl.feriados.external.model.Token;
import br.ufg.es.iapl.feriados.external.util.RequestUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class BankApiManager {

//    public static BankApiManager bankApiManager = new BankApiManager();
    public static String accessToken = null;

//    public static void main(String[] args) {
//        try {
//            bankApiManager.listAllAccounts();
//            bankApiManager.account("1");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * @return the Token to be used in future requests
     */
    public Token login() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(Constants.CREDENTIALS, RequestUtil.defaultHeaders());
        ResponseEntity<Token> response = restTemplate.postForEntity(Constants.API_URL + Constants.ENDPOINT_LOGIN, entity, Token.class);

        return response.getBody();
    }

    public List<Account> listAllAccounts() {
        if (accessToken == null) {
            accessToken = login().getValue().token;
        }

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity = new HttpEntity<>(Constants.CREDENTIALS, RequestUtil.tokenHeader(accessToken));
        ResponseEntity<AccountsContent> responseEntity = restTemplate.exchange(Constants.API_URL + Constants.ENDPOINT_LIST_ACCOUNTS, HttpMethod.GET, entity, AccountsContent.class);
        List<Account> accountsArray = responseEntity.getBody().value;
        return accountsArray;
    }

    public Account account(String id) {
        if (accessToken == null) {
            accessToken =login().getValue().token;
        }

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity = new HttpEntity<>(Constants.CREDENTIALS, RequestUtil.tokenHeader(accessToken));
        String endpointQuery = Constants.ENDPOINT_SINGLE_ACCOUNT.replace("{id}", id);
        ResponseEntity<AccountContent> responseEntity = restTemplate.exchange(Constants.API_URL + endpointQuery, HttpMethod.GET, entity, AccountContent.class);
        return responseEntity.getBody().getValue();
    }
}