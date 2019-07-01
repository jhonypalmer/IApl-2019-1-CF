package br.ufg.es.iapl.feriados.repository


import br.ufg.es.iapl.feriados.model.region.Country
import br.ufg.es.iapl.feriados.model.region.State
import groovy.transform.CompileStatic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
@CompileStatic
interface StateRepository extends JpaRepository<State, Long> {

	State findByCountryAndName(Country country, String name)

	State findByName(String name)

	@Query(value = "SELECT s FROM State s LEFT JOIN FETCH s.country c")
	List<State> findAllFetch();

}
