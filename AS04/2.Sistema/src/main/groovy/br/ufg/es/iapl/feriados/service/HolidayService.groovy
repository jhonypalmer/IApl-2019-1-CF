package br.ufg.es.iapl.feriados.service

import br.ufg.es.iapl.feriados.dto.HolidayDTO
import br.ufg.es.iapl.feriados.dto.HolidaysDTO
import br.ufg.es.iapl.feriados.exception.ResourceNotFoundException
import br.ufg.es.iapl.feriados.model.Holiday
import br.ufg.es.iapl.feriados.model.definition.MonthDayHolidayDate
import br.ufg.es.iapl.feriados.model.region.City
import br.ufg.es.iapl.feriados.model.region.Country
import br.ufg.es.iapl.feriados.model.region.Region
import br.ufg.es.iapl.feriados.model.region.State
import br.ufg.es.iapl.feriados.repository.CityRepository
import br.ufg.es.iapl.feriados.repository.CountryRepository
import br.ufg.es.iapl.feriados.repository.HolidayRepository
import br.ufg.es.iapl.feriados.repository.StateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.time.Year

@Service
class HolidayService {

	@Autowired
	private HolidayRepository holidayRepository

	@Autowired
	private CityRepository cityRepository

	@Autowired
	private StateRepository stateRepository

	@Autowired
	private CountryRepository countryRepository

	HolidaysDTO findAllHolidays(Integer year) {
		HolidaysDTO holidaysDTO = new HolidaysDTO(year: year)
		List<HolidayDTO> holidayDTOS = new ArrayList<>()

		List<Holiday> holidays = holidayRepository.findAll()
		holidays.forEach({ holiday -> holidayDTOS.add(convertHolidayToDTO(holiday, year)) })

		holidaysDTO.setHolidays(holidayDTOS)

		return holidaysDTO
	}

	HolidaysDTO findHolidayById(Long id) {
		HolidaysDTO holidaysDTO = new HolidaysDTO()
		Holiday holiday = findById(id)

		holidaysDTO.setHolidays(Arrays.asList(convertHolidayToDTO(holiday, Year.now().value)))

		return holidaysDTO
	}

	void deleteHolidayById(Long id) {
		Holiday holiday = findById(id)
		holidayRepository.deleteById(id)
	}

	private Holiday findById(Long id) {
		Optional<Holiday> holiday = holidayRepository.findById(id)

		if (!holiday.isPresent()) {
			throw new ResourceNotFoundException("No such holiday")
		}

		return holiday.get()
	}

	private HolidayDTO convertHolidayToDTO(Holiday holiday, int year) {
		HolidayDTO holidayDTO = new HolidayDTO()

		if (holiday.getId() != null) {
			holidayDTO.setId(holiday.getId())
		}

		holidayDTO.setDescription(holiday.getDescription())
		holidayDTO.setDate(holiday.getDateDefinition().getDate(year))

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

	void udpateHolidays(HolidaysDTO holidaysDTO) {
		for (HolidayDTO holidayDTO : holidaysDTO.getHolidays()) {
			Holiday holiday = findById(holidayDTO.getId())
			holiday.setDescription(holidayDTO.getDescription())

			MonthDayHolidayDate monthDayHolidayDate = new MonthDayHolidayDate()
			monthDayHolidayDate.setDayOfMonth(holidayDTO.getDate().getDayOfMonth())
			monthDayHolidayDate.setMonth(holidayDTO.getDate().getMonth())
			holiday.setDateDefinition(monthDayHolidayDate)

			Region region = null

			if (holidayDTO.getCity() != null && !holidayDTO.getCity().isEmpty()) {
				region = cityRepository.findByName(holidayDTO.getCity())
			} else if (holidayDTO.getState() != null && !holidayDTO.getState().isEmpty()) {
				region = stateRepository.findByName(holidayDTO.getState())
			} else {
				region = countryRepository.findByName(holidayDTO.getCountry())
			}

			holiday.setRegion(region)
			holidayRepository.save(holiday)
		}

	}

	HolidaysDTO saveHolidays(HolidaysDTO holidaysDTO) {
		HolidaysDTO holidaysDTOSave = new HolidaysDTO()
		List<HolidayDTO> listholidaysSave = new ArrayList<>();
		for (HolidayDTO holidayDTO : holidaysDTO.getHolidays()) {
			Holiday holiday = new Holiday()
			holiday.setDescription(holidayDTO.getDescription())

			MonthDayHolidayDate monthDayHolidayDate = new MonthDayHolidayDate()
			monthDayHolidayDate.setDayOfMonth(holidayDTO.getDate().getDayOfMonth())
			monthDayHolidayDate.setMonth(holidayDTO.getDate().getMonth())
			holiday.setDateDefinition(monthDayHolidayDate)

			Region region = null

			if (holidayDTO.getCity() != null && !holidayDTO.getCity().isEmpty()) {
				region = cityRepository.findByName(holidayDTO.getCity())
			} else if (holidayDTO.getState() != null && !holidayDTO.getState().isEmpty()) {
				region = stateRepository.findByName(holidayDTO.getState())
			} else {
				region = countryRepository.findByName(holidayDTO.getCountry())
			}

			holiday.setRegion(region)
			Holiday holidaySave = holidayRepository.save(holiday)

			listholidaysSave.add(convertHolidayToDTO(holidaySave, Year.now().value))
		}
		holidaysDTOSave.setHolidays(listholidaysSave)
		return holidaysDTOSave;

	}

}
