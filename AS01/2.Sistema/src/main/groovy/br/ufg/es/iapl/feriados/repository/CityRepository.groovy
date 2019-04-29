package br.ufg.es.iapl.feriados.repository

import br.ufg.es.iapl.feriados.model.region.City
import br.ufg.es.iapl.feriados.model.region.Country
import groovy.transform.CompileStatic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
@CompileStatic
interface CityRepository extends JpaRepository<City, Long> {

	City findByCountryAndName(Country country, String name)

}
