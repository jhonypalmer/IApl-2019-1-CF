package br.ufg.es.iapl.feriados.model.definition

import br.ufg.es.iapl.feriados.model.Holiday

import java.time.LocalDate

class HolidayRelativeDate implements DateDefinition {

	Holiday holiday
	int daysOffset

	@Override
	LocalDate getDate(int year) {
		LocalDate referenceHolidayDate = holiday.getDate(year)

		return referenceHolidayDate.plusDays(daysOffset)
	}
}
