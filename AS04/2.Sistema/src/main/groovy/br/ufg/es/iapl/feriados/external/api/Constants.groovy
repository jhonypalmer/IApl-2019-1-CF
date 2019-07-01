package br.ufg.es.iapl.feriados.external.api

import groovy.transform.CompileStatic

@CompileStatic
class Constants {
	public static String API_URL = "http://bancolegal.azurewebsites.net/api/"
	public static String CREDENTIALS = "{\"email\": \"saulo@gmail.com\",\"senha\": \"1234admin\",\"internacionalizacao\" : \"2\"}\""

	//Endpoints
	public static String ENDPOINT_LOGIN = "login"
	public static String ENDPOINT_LIST_ACCOUNTS = "conta"
	public static String ENDPOINT_SINGLE_ACCOUNT = "conta/{id}"
}
