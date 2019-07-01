package br.ufg.es.iapl.feriados.controller

import br.ufg.es.iapl.feriados.model.region.City
import br.ufg.es.iapl.feriados.repository.CityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("city")
class CityController {

    @Autowired
    private CityRepository cityRepository

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<City> getCities() {
        return cityRepository.findAllFetch()
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/{state}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<City> getCitiesByState(@PathVariable String state) {
        return cityRepository.findAllByStateFetch(state)
    }

}
