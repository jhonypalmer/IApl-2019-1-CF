package br.ufg.es.iapl.feriados.model.definition

import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.Entity
import java.time.LocalDate
import java.time.Month
import java.time.MonthDay

@Entity
@CompileStatic
class MonthDayHolidayDate extends DateDefinition {

	public static final DateDefinition NEW_YEARS_DAY = new MonthDayHolidayDate(
			month: Month.JANUARY,
			dayOfMonth: 1,
	)

	@Column
	Month month

	int dayOfMonth

	@Override
	LocalDate getDate(int year) {
		return LocalDate.of(year, monthDay.month, monthDay.dayOfMonth)
	}

	transient MonthDay getMonthDay() {
		return MonthDay.of(this.month, this.dayOfMonth)
	}
}
