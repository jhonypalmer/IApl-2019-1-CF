package br.ufg.es.iapl.feriados.service


import br.ufg.es.iapl.feriados.dto.MonthDayHoliday
import br.ufg.es.iapl.feriados.dto.MonthDayHolidayResultList
import br.ufg.es.iapl.feriados.exception.ResourceNotFoundException
import br.ufg.es.iapl.feriados.model.Holiday
import br.ufg.es.iapl.feriados.model.definition.DateDefinition
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
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

import java.time.Month

@Service
@CompileStatic
class HolidayService {

	@Autowired
	private HolidayRepository holidayRepository

	@Autowired
	private CityRepository cityRepository

	@Autowired
	private StateRepository stateRepository

	@Autowired
	private CountryRepository countryRepository

	MonthDayHolidayResultList listHolidays(Pageable pagable) {
		MonthDayHolidayResultList holidayResultList = new MonthDayHolidayResultList()
		List<MonthDayHoliday> holidayList = new ArrayList<>()

		Page<Holiday> holidays = holidayRepository.findAll(pagable)
		holidays.forEach({ holiday -> holidayList.add(convertHolidayToMonthDayHoliday(holiday)) })

		holidayResultList.setHolidays(holidayList)
		holidayResultList.total = holidays.totalElements

		return holidayResultList
	}

	MonthDayHoliday findHolidayById(Long id) {
		Holiday holiday = findById(id)

		return convertHolidayToMonthDayHoliday(holiday)
	}

	void deleteHolidayById(Long id) {
		findById(id)
		holidayRepository.deleteById(id)
	}

	private Holiday findById(Long id) {
		Optional<Holiday> holiday = holidayRepository.findById(id)

		if (!holiday.isPresent()) {
			throw new ResourceNotFoundException("No such holiday")
		}

		return holiday.get()
	}

	private MonthDayHoliday convertHolidayToMonthDayHoliday(Holiday holiday) {
		MonthDayHoliday holidayDTO = new MonthDayHoliday(
				id: holiday.id,
				description: holiday.description,
		)

		DateDefinition definition = holiday.dateDefinition
		if (definition instanceof MonthDayHolidayDate) {
			holidayDTO.month = definition.month.value
			holidayDTO.dayOfMonth = definition.dayOfMonth
		}

		Optional<City> city = cityRepository.findById(holiday.getRegion().getId())
		Optional<State> state = stateRepository.findById(holiday.getRegion().getId())
		Optional<Country> country = countryRepository.findById(holiday.getRegion().getId())

		if (country.isPresent()) {
			holidayDTO.setCountry(country.get().getName())
		} else if (state.isPresent()) {
			holidayDTO.setState(state.get().getName())
			holidayDTO.setState(state.get().getCountry().getName())
		} else if (city.isPresent()) {
			holidayDTO.setCity(city.get().getName())
			holidayDTO.setState(city.get().getState().getName())
			holidayDTO.setCountry(city.get().getState().getCountry().getName())
		}

		return holidayDTO
	}

	MonthDayHoliday saveHoliday(MonthDayHoliday holidayDTO) {
		MonthDayHolidayDate monthDayHolidayDate = new MonthDayHolidayDate(
				dayOfMonth: holidayDTO.dayOfMonth,
				month: Month.of(holidayDTO.month)
		)

		Region region
		if (holidayDTO.city) {
			region = cityRepository.findByName(holidayDTO.getCity())
		} else if (holidayDTO.state) {
			region = stateRepository.findByName(holidayDTO.state)
		} else {
			region = countryRepository.findByName(holidayDTO.getCountry())
		}

		Holiday holiday = new Holiday(
				description: holidayDTO.description,
				dateDefinition: monthDayHolidayDate,
				region: region
		)

		Holiday savedHoliday = holidayRepository.save(holiday)

		return convertHolidayToMonthDayHoliday(savedHoliday)
	}

	void updateHoliday(MonthDayHoliday holidayDTO) {
		MonthDayHolidayDate monthDayHolidayDate = new MonthDayHolidayDate(
				dayOfMonth: holidayDTO.dayOfMonth,
				month: Month.of(holidayDTO.month)
		)

		Region region
		if (holidayDTO.city) {
			region = cityRepository.findByName(holidayDTO.city)
		} else if (holidayDTO.state) {
			region = stateRepository.findByName(holidayDTO.state)
		} else {
			region = countryRepository.findByName(holidayDTO.country)
		}

		Optional<Holiday> holidayQuery = holidayRepository.findById(holidayDTO.id)

		if (!holidayQuery.isPresent()) {
			throw new ResourceNotFoundException("No such holiday")
		}

		Holiday holiday = holidayQuery.get()
		holiday.description = holidayDTO.description
		holiday.dateDefinition = monthDayHolidayDate
		holiday.region = region

		holidayRepository.save(holiday)
	}

}
