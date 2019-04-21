package br.ufg.es.iapl.feriados.model

import java.time.LocalDate

interface HolidayDate {
	abstract LocalDate getDate(int year)
}
