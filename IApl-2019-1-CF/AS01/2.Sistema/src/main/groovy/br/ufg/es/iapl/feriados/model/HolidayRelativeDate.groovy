package br.ufg.es.iapl.feriados.model

import java.time.LocalDate

class HolidayRelativeDate implements HolidayDate {

	Holiday holiday
	int daysOffset

	@Override
	LocalDate getDate(int year) {
		LocalDate referenceHolidayDate = holiday.getDate(year)

		return referenceHolidayDate.plusDays(daysOffset)
	}
}
