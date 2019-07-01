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

	@Autowired
	private HolidayService holidayService

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	HolidaysDTO getHolidays() {
		return holidayService.findAllHolidays(Year.now().value)
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	HolidaysDTO getHoliday(@PathVariable Long id) {
		return holidayService.findHolidayById(id)
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<HolidaysDTO> updateHoliday(@RequestBody HolidaysDTO holidaysDTO) {
		holidayService.udpateHolidays(holidaysDTO)
		return ResponseEntity.ok(holidaysDTO)
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<HolidaysDTO> saveHolidays(@RequestBody HolidaysDTO holidaysDTO) {
		holidayService.saveHolidays(holidaysDTO)
		return ResponseEntity.ok(holidaysDTO)
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	void deleteHoliday(@PathVariable Long id) {
		holidayService.deleteHolidayById(id)
	}
}
