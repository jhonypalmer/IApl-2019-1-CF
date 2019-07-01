package br.ufg.es.iapl.feriados.controller

import br.ufg.es.iapl.feriados.model.region.Country
import br.ufg.es.iapl.feriados.repository.CountryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("country")
@CrossOrigin(origins = "*")
class CountryController {

	@Autowired
	private CountryRepository countryRepository

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	List<Country> getCountries() {
		return countryRepository.findAll()
	}

}
