package br.ufg.es.iapl.feriados

import br.ufg.es.iapl.feriados.service.DatabaseSeederService
import br.ufg.es.iapl.feriados.service.HolidayCacheService
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

import javax.annotation.PostConstruct

@CompileStatic
@SpringBootApplication
class FeriadosApplication {

	@Autowired
	DatabaseSeederService databaseSeederService

	@Autowired
	HolidayCacheService holidayCacheService

	static void main(String[] args) {
		SpringApplication.run(FeriadosApplication, args)
	}

	@PostConstruct
	private void init() {
		databaseSeederService.seedRegions()
		databaseSeederService.seedHolidays()
	}

}
