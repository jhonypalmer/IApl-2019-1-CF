package br.ufg.es.iapl.feriados.controller

import br.ufg.es.iapl.feriados.model.region.State
import br.ufg.es.iapl.feriados.repository.StateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("state")
@CrossOrigin(origins = "*")
class StateController {

	@Autowired
	private StateRepository stateRepository

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	List<State> getStates() {
		return stateRepository.findAllFetch()
	}

	@GetMapping(value = "/{country}", produces = MediaType.APPLICATION_JSON_VALUE)
	List<State> getStatesByCountry(@PathVariable String country) {
		return stateRepository.findAllByCountryFetch(country)
	}

}
