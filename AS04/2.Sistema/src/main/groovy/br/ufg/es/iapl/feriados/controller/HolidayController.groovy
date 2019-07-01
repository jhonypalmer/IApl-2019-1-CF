package br.ufg.es.iapl.feriados.controller


import br.ufg.es.iapl.feriados.dto.MonthDayHoliday
import br.ufg.es.iapl.feriados.dto.MonthDayHolidayResultList
import br.ufg.es.iapl.feriados.service.HolidayService
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CompileStatic
@RestController
@RequestMapping('holiday')
@CrossOrigin(origins = "*")
class HolidayController {

	private static final String APPLICATION_FIXED_POSITION = "application/fixedPosition"

	@Autowired
	private HolidayService holidayService

	@GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, APPLICATION_FIXED_POSITION])
	MonthDayHolidayResultList listMonthDayHolidays(
			@RequestParam(required = false, defaultValue = '0') Integer page,
			@RequestParam(required = false, defaultValue = '20') Integer size
	) {
		page = page ?: 0
		size = Integer.min(size, 50)
		Pageable pageable = PageRequest.of(page, size)

		return holidayService.listHolidays(pageable)
	}

	@GetMapping(value = "/{id}", produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, APPLICATION_FIXED_POSITION])
	MonthDayHoliday getHoliday(@PathVariable Long id) {
		return holidayService.findHolidayById(id)
	}

	@PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, APPLICATION_FIXED_POSITION], produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, APPLICATION_FIXED_POSITION])
	MonthDayHoliday saveHoliday(@RequestBody MonthDayHoliday monthDayHoliday) {
		MonthDayHoliday saveHoliday = holidayService.saveHoliday(monthDayHoliday)

		return saveHoliday
	}

	@PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, APPLICATION_FIXED_POSITION], produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, APPLICATION_FIXED_POSITION])
	ResponseEntity<MonthDayHoliday> updateHoliday(@RequestBody MonthDayHoliday monthDayHoliday) {
		holidayService.updateHoliday(monthDayHoliday)

		return ResponseEntity.ok(monthDayHoliday)
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	void deleteHoliday(@PathVariable Long id) {
		holidayService.deleteHolidayById(id)
	}
}
