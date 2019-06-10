package br.ufg.es.iapl.feriados.model.definition

import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.Entity
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters

@Entity
@CompileStatic
class RelativeDayOfWeekDate extends DateDefinition {

	@Column(nullable = true)
	int ordinal

	@Column(nullable = true)
	DayOfWeek dayOfWeek

	@Column(nullable = true)
	Month month

	@Override
	LocalDate getDate(int year) {
		YearMonth yearMonth = YearMonth.of(year, month)

		return yearMonth.atDay(1).with(TemporalAdjusters.dayOfWeekInMonth(ordinal, dayOfWeek))
	}
}
