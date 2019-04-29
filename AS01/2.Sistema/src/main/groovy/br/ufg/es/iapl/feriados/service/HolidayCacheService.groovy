package br.ufg.es.iapl.feriados.service

import br.ufg.es.iapl.feriados.model.Holiday
import br.ufg.es.iapl.feriados.model.HolidayCache
import br.ufg.es.iapl.feriados.model.region.City
import br.ufg.es.iapl.feriados.model.region.Country
import br.ufg.es.iapl.feriados.repository.CountryRepository
import br.ufg.es.iapl.feriados.repository.HolidayCacheRepository
import br.ufg.es.iapl.feriados.repository.HolidayRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.transaction.Transactional

@Service
class HolidayCacheService {

	@Autowired
	HolidayRepository holidayRepository

	@Autowired
	HolidayCacheRepository holidayCacheRepository

	@Autowired
	CountryRepository countryRepository

	@Transactional
	buildHolidayCache(int year) {
		for (Holiday holiday in holidayRepository.findAll()) {
			// TODO: buscar pelo ano
			if (holidayCacheRepository.findByRegionAndDescription(holiday.region, holiday.description)) {
				continue
			}

			HolidayCache holidayCache = new HolidayCache(
					region: holiday.region,
					description: holiday.description,
					date: holiday.getDate(year)
			)
			holidayCacheRepository.save(holidayCache)
		}
	}

	String buildPositionalFile() {
		StringBuilder stringBuilder = new StringBuilder()
		countryRepository.findAll().each { Country country ->
			List<HolidayCache> holidayCaches = holidayCacheRepository.findAllByRegion(country)

			if (holidayCaches) {
				appendPositions(stringBuilder, 'COUNTRY', 7)
				appendPositions(stringBuilder, country.abbreviation, 2)
				appendPositions(stringBuilder, country.name, 10)
				stringBuilder << '\n'
				holidayCaches.each { HolidayCache holidayCache ->
					appendPositions(stringBuilder, holidayCache.description, 30)
					appendPositions(stringBuilder, holidayCache.date.toString(), 10)
					stringBuilder << '\n'
				}
				stringBuilder << '\n'
			}
		}

		return stringBuilder.toString()
	}

	void importPositionalFile(String text) {
		
	}

	private static StringBuilder appendPositions(final StringBuilder stringBuilder, final String value, int positions) {
		String positionalValue = value.padRight(positions).substring(0, positions)
		stringBuilder << positionalValue
	}

}
