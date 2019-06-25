package br.ufg.es.iapl.feriados.controller

import br.ufg.es.iapl.feriados.dto.HolidaysDTO
import br.ufg.es.iapl.feriados.service.HolidayService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import java.time.Year

@RestController
@RequestMapping("holiday")
class HolidayController {

	private static final String APPLICATION_FIXED_POSITION = "application/fixedPosition"

	@Autowired
	private HolidayService holidayService

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, APPLICATION_FIXED_POSITION])
	HolidaysDTO getHolidays() {
		return holidayService.findAllHolidays(Year.now().value)
	}

	@GetMapping(value = "/{id}", produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, APPLICATION_FIXED_POSITION])
	HolidaysDTO getHoliday(@PathVariable Long id) {
		return holidayService.findHolidayById(id)
	}

	@PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, APPLICATION_FIXED_POSITION])
	ResponseEntity<HolidaysDTO> saveHolidays(@RequestBody HolidaysDTO holidaysDTO) {
		holidayService.saveHolidays(holidaysDTO)
		return ResponseEntity.ok(holidaysDTO)
	}
}
