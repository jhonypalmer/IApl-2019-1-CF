package br.ufg.es.iapl.feriados

import groovy.transform.CompileStatic
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@CompileStatic
@SpringBootApplication
class FeriadosApplication {

	static void main(String[] args) {
		SpringApplication.run(FeriadosApplication, args)
	}

}
