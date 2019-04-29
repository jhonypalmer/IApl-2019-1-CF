package br.ufg.es.iapl.feriados.repository

import br.ufg.es.iapl.feriados.model.region.Country
import groovy.transform.CompileStatic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
@CompileStatic
interface CountryRepository extends JpaRepository<Country, Long> {

	Country findByAbbreviation(String abbreviation)

	Country findByName(String name)

}
