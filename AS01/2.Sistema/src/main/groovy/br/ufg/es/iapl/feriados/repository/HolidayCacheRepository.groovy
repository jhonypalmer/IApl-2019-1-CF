package br.ufg.es.iapl.feriados.repository

import br.ufg.es.iapl.feriados.model.Holiday
import br.ufg.es.iapl.feriados.model.HolidayCache
import br.ufg.es.iapl.feriados.model.region.Region
import groovy.transform.CompileStatic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
@CompileStatic
interface HolidayCacheRepository extends JpaRepository<HolidayCache, Long> {

	HolidayCache findByRegionAndDescription(Region region, String description)

	List<HolidayCache> findAllByRegion(Region region)

}
