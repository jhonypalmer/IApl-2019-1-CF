package br.ufg.es.iapl.feriados.service

import br.ufg.es.iapl.feriados.dto.AppliedHoliday
import br.ufg.es.iapl.feriados.dto.AppliedHolidayResultList
import br.ufg.es.iapl.feriados.model.Holiday
import br.ufg.es.iapl.feriados.model.region.City
import br.ufg.es.iapl.feriados.model.region.Country
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

@Service
@CompileStatic
class AppliedHolidayService {

	@Autowired
	private HolidayRepository holidayRepository

	@Autowired
	private CityRepository cityRepository

	@Autowired
	private StateRepository stateRepository

	@Autowired
	private CountryRepository countryRepository

	AppliedHolidayResultList listAppliedHolidayByYear(Integer year, Pageable pagable) {
		AppliedHolidayResultList holidayResultList = new AppliedHolidayResultList(year: year)
		List<AppliedHoliday> holidayList = new ArrayList<>()

		Page<Holiday> holidays = holidayRepository.findAll(pagable)
		holidays.forEach({ holiday -> holidayList.add(convertHolidayToApplied(holiday, year)) })

		holidayResultList.setHolidays(holidayList)
		holidayResultList.total = holidays.totalElements

		return holidayResultList
	}

	private AppliedHoliday convertHolidayToApplied(Holiday holiday, int year) {
		AppliedHoliday holidayDTO = new AppliedHoliday()

		holidayDTO.description = holiday.getDescription()
		holidayDTO.date = holiday.dateDefinition.getDate(year)

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

}
