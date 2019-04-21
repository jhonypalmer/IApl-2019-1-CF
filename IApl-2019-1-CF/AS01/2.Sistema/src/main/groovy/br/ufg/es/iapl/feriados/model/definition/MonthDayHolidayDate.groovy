package br.ufg.es.iapl.feriados.model.definition

import java.time.LocalDate
import java.time.Month
import java.time.MonthDay

class MonthDayHolidayDate implements DateDefinition {
	public static final DateDefinition NEW_YEARS_DAY = new MonthDayHolidayDate(
			monthDay: MonthDay.of(Month.JANUARY, 1)
	)

	MonthDay monthDay

	@Override
	LocalDate getDate(int year) {
		return LocalDate.of(year, monthDay.month, monthDay.dayOfMonth);
	}
}
