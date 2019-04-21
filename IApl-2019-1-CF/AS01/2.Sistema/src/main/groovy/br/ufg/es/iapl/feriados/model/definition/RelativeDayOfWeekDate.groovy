package br.ufg.es.iapl.feriados.model.definition

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters

class RelativeDayOfWeekDate implements DateDefinition {

	int ordinal
	DayOfWeek dayOfWeek
	Month month

	@Override
	LocalDate getDate(int year) {
		YearMonth yearMonth = YearMonth.of(year, month)

		return yearMonth.atDay(1).with(TemporalAdjusters.dayOfWeekInMonth(ordinal, dayOfWeek))
	}
}
