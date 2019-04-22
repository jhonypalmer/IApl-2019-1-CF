package br.ufg.es.iapl.feriados.repository

import br.ufg.es.iapl.feriados.model.region.City
import groovy.transform.CompileStatic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
@CompileStatic
interface CityRepository extends JpaRepository<City, Long> {
}
