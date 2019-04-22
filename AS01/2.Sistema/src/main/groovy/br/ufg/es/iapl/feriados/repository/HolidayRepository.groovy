package br.ufg.es.iapl.feriados.repository

import br.ufg.es.iapl.feriados.model.Holiday
import br.ufg.es.iapl.feriados.model.region.Region
import groovy.transform.CompileStatic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
@CompileStatic
interface HolidayRepository extends JpaRepository<Holiday, Long> {

	Holiday findByRegionAndDescription(Region region, String description)

}
