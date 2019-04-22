package br.ufg.es.iapl.feriados.service

import br.ufg.es.iapl.feriados.model.region.City
import br.ufg.es.iapl.feriados.model.region.Country
import br.ufg.es.iapl.feriados.model.region.State
import br.ufg.es.iapl.feriados.repository.CityRepository
import br.ufg.es.iapl.feriados.repository.CountryRepository
import br.ufg.es.iapl.feriados.repository.StateRepository
import groovy.transform.CompileStatic
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@CompileStatic
class DatabaseSeederService {

	@Autowired
	CountryRepository countryRepository

	@Autowired
	StateRepository stateRepository

	@Autowired
	CityRepository cityRepository

	void seedRegions() {
		if (cityRepository.count() != 0) {
			return
		}
		InputStream citiesCsvInputStream = DatabaseSeederService.getResourceAsStream('/worldcities.csv')
		Reader reader = new InputStreamReader(citiesCsvInputStream)
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader)

		Map<String, Country> countries = [:]
		Map<String, State> states = [:]

		for (CSVRecord record : records) {
			String cityName = record.get('city_ascii')
			String stateName = record.get('admin_name')
			String countryName = record.get('country')
			String countryAbbrev = record.get('iso2')

			final Country country
			final Country countryFromCache = countries[countryAbbrev]
			if (countryFromCache) {
				country = countryFromCache
			} else {
				country = new Country(
						name: countryName,
						abbreviation: countryAbbrev
				)
				countryRepository.save(country)
				countries.put(countryAbbrev, country)
			}

			final State state
			final State stateFromCache = states[stateName]
			if (stateFromCache) {
				state = stateFromCache
			} else if (stateName) {
				state = new State(
						name: stateName,
						country: country,
				)
				stateRepository.save(state)
				states.put(stateName, state)
			} else {
				state = null
			}

			City city = new City(
					name: cityName,
					state: state,
					country: country,
			)
			cityRepository.save(city)
		}

		countryRepository.findByAbbreviation('')
	}

	void seedHolidays() {
		// TODO
	}

}
