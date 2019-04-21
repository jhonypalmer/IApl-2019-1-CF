package br.ufg.es.iapl.feriados.model

import java.time.LocalDate
import java.time.Month
import java.time.MonthDay

class MonthDayHolidayDate implements HolidayDate {
	public static final HolidayDate NEW_YEARS_DAY = new MonthDayHolidayDate(
			monthDay: MonthDay.of(Month.JANUARY, 1)
	)

	MonthDay monthDay

	@Override
	LocalDate getDate(int year) {
		return LocalDate.of(year, monthDay.month, monthDay.dayOfMonth);
	}
}
