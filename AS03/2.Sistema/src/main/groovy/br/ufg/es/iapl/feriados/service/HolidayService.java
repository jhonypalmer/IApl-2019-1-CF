package br.ufg.es.iapl.feriados.service;

import br.ufg.es.iapl.feriados.dto.HolidayDTO;
import br.ufg.es.iapl.feriados.dto.HolidaysDTO;
import br.ufg.es.iapl.feriados.model.Holiday;
import br.ufg.es.iapl.feriados.model.definition.DateDefinition;
import br.ufg.es.iapl.feriados.model.definition.MonthDayHolidayDate;
import br.ufg.es.iapl.feriados.model.region.City;
import br.ufg.es.iapl.feriados.model.region.Country;
import br.ufg.es.iapl.feriados.model.region.Region;
import br.ufg.es.iapl.feriados.model.region.State;
import br.ufg.es.iapl.feriados.repository.CityRepository;
import br.ufg.es.iapl.feriados.repository.CountryRepository;
import br.ufg.es.iapl.feriados.repository.HolidayRepository;
import br.ufg.es.iapl.feriados.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@Service
public class HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryRepository countryRepository;

    public HolidaysDTO findAllHolidays() {
        HolidaysDTO holidaysDTO = new HolidaysDTO();
        List<HolidayDTO> holidayDTOS = new ArrayList<>();

        List<Holiday> holidays = holidayRepository.findAll();
        holidays.forEach(holiday -> holidayDTOS.add(convertHolidayToDTO(holiday)));

        holidaysDTO.setHolidays(holidayDTOS);

        return holidaysDTO;
    }

    public HolidaysDTO findHolidayById(Long id) {
        HolidaysDTO holidaysDTO = new HolidaysDTO();
        Holiday holiday = holidayRepository.findById(id).get();

        holidaysDTO.setHolidays(Arrays.asList(convertHolidayToDTO(holiday)));

        return holidaysDTO;
    }

    private HolidayDTO convertHolidayToDTO(Holiday holiday) {
        HolidayDTO holidayDTO = new HolidayDTO();
        holidayDTO.setDescription(holiday.getDescription());

        int year = Calendar.getInstance().get(Calendar.YEAR);
        holidayDTO.setDate(holiday.getDateDefinition().getDate(year));

        Optional<City> city = cityRepository.findById(holiday.getRegion().getId());
        Optional<State> state = stateRepository.findById(holiday.getRegion().getId());
        Optional<Country> country = countryRepository.findById(holiday.getRegion().getId());

        if (country.isPresent()) {
            holidayDTO.setCountry(country.get().getName());
        } else if (state.isPresent()) {
            holidayDTO.setState(state.get().getName());
            holidayDTO.setState(state.get().getCountry().getName());
        } else if (city.isPresent()) {
            holidayDTO.setCity(city.get().getName());
            holidayDTO.setState(city.get().getState().getName());
            holidayDTO.setCountry(city.get().getState().getCountry().getName());
        }

        return holidayDTO;
    }

    public void saveHolidays(HolidaysDTO holidaysDTO) {
        for (HolidayDTO holidayDTO : holidaysDTO.getHolidays()) {
            Holiday holiday = new Holiday();
            holiday.setDescription(holidayDTO.getDescription());

            MonthDayHolidayDate monthDayHolidayDate = new MonthDayHolidayDate();
            monthDayHolidayDate.setDayOfMonth(holidayDTO.getDate().getDayOfMonth());
            monthDayHolidayDate.setMonth(holidayDTO.getDate().getMonth());
            holiday.setDateDefinition(monthDayHolidayDate);

            Region region = null;

            if (holidayDTO.getCity() != null && !holidayDTO.getCity().isEmpty()) {
                region = cityRepository.findByName(holidayDTO.getCity());
            } else if (holidayDTO.getState() != null && !holidayDTO.getState().isEmpty()) {
                region = stateRepository.findByName(holidayDTO.getState());
            } else {
                region = countryRepository.findByName(holidayDTO.getCountry());
            }

            holiday.setRegion(region);
            holidayRepository.save(holiday);
        }

    }
}
