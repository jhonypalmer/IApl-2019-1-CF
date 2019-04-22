package br.ufg.es.iapl.feriados.repository

import br.ufg.es.iapl.feriados.model.region.Country
import br.ufg.es.iapl.feriados.model.region.State
import groovy.transform.CompileStatic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
@CompileStatic
interface StateRepository extends JpaRepository<State, Long> {

	State findByCountryAndName(Country country, String name)
}
