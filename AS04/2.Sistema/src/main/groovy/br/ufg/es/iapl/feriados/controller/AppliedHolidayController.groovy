package br.ufg.es.iapl.feriados.controller

import br.ufg.es.iapl.feriados.dto.AppliedHolidayResultList
import br.ufg.es.iapl.feriados.service.AppliedHolidayService
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@CompileStatic
@RestController
@RequestMapping("appliedHoliday")
@CrossOrigin(origins = "http://localhost:4200")
class AppliedHolidayController {

	private static final String APPLICATION_FIXED_POSITION = "application/fixedPosition"

	@Autowired
	private AppliedHolidayService appliedHolidayService

	@GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, APPLICATION_FIXED_POSITION])
	AppliedHolidayResultList listHolidays(
			@RequestParam(required = true) Integer year,
			@RequestParam(required = false, defaultValue = '0') Integer page,
			@RequestParam(required = false, defaultValue = '20') Integer size
	) {
		page = page ?: 0
		size = Integer.min(size, 50)
		Pageable pageable = PageRequest.of(page, size)

		return appliedHolidayService.listAppliedHolidayByYear(year, pageable)
	}

}
