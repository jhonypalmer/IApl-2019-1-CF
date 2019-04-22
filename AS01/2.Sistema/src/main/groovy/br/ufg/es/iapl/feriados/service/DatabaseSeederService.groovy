package br.ufg.es.iapl.feriados.service

import br.ufg.es.iapl.feriados.model.Holiday
import br.ufg.es.iapl.feriados.model.definition.Computus
import br.ufg.es.iapl.feriados.model.definition.DateDefinition
import br.ufg.es.iapl.feriados.model.definition.HolidayRelativeDate
import br.ufg.es.iapl.feriados.model.definition.MonthDayHolidayDate
import br.ufg.es.iapl.feriados.model.region.City
import br.ufg.es.iapl.feriados.model.region.Country
import br.ufg.es.iapl.feriados.model.region.Region
import br.ufg.es.iapl.feriados.model.region.State
import br.ufg.es.iapl.feriados.repository.CityRepository
import br.ufg.es.iapl.feriados.repository.CountryRepository
import br.ufg.es.iapl.feriados.repository.HolidayRepository
import br.ufg.es.iapl.feriados.repository.StateRepository
import groovy.transform.CompileStatic
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.time.Month

@Service
@CompileStatic
class DatabaseSeederService {

	@Autowired
	CountryRepository countryRepository

	@Autowired
	StateRepository stateRepository

	@Autowired
	CityRepository cityRepository

	@Autowired
	HolidayRepository holidayRepository

	void seedRegions() {
		if (cityRepository.count() != 0) {
			return
		}
		InputStream citiesCsvInputStream = DatabaseSeederService.getResourceAsStream('/seed/worldcities.csv')
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
		if (holidayRepository.count() != 0) {
			return
		}

		InputStream citiesCsvInputStream = DatabaseSeederService.getResourceAsStream('/seed/holidays/br.csv')
		Reader reader = new InputStreamReader(citiesCsvInputStream)
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader)

		for (CSVRecord record : records) {
			String countryAbbrev = record.get('country')
			String stateName = record.get('state')
			String cityName = record.get('city')
			String description = record.get('description')
			String type = record.get('type')
			Integer month = record.get('month').toInteger()
			Integer dayOfMonth = record.get('dayOfMonth') ? record.get('dayOfMonth').toInteger() : null
			Integer dayOfWeek = record.get('dayOfWeek') ? record.get('dayOfWeek').toInteger() : null
			Integer ordinal = record.get('ordinal') ? record.get('ordinal').toInteger() : null
			String relativeHolidayDesc = record.get('holiday')
			Integer daysOffset = record.get('daysOffset') ? record.get('daysOffset').toInteger() : null

			Country country = countryRepository.findByAbbreviation(countryAbbrev)

			Region region
			if (cityName) {
				region = cityRepository.findByCountryAndName(country, cityName)
			} else if (stateName) {
				region = stateRepository.findByCountryAndName(country, stateName)
			} else {
				region = country
			}

			DateDefinition dateDefinition = null

			switch (type) {
				case 'MonthDayHoliday':
					dateDefinition = new MonthDayHolidayDate(
							month: Month.of(month),
							dayOfMonth: dayOfMonth,
					)
					break
				case 'Computus':
					dateDefinition = Computus.COMPUTUS
					break
				case 'HolidayRelative':
					Holiday relativeHoliday = holidayRepository.findByRegionAndDescription(region, relativeHolidayDesc)
					dateDefinition = new HolidayRelativeDate(
							holiday: relativeHoliday,
							daysOffset: daysOffset,
					)
					break
			}

			Holiday holiday = new Holiday(
					description: description,
					region: region,
					dateDefinition: dateDefinition,
			)

			holidayRepository.save(holiday)
		}
	}

}
