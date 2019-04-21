package br.ufg.es.iapl.feriados.model

import java.time.LocalDate

class Holiday {

	Region region
	String description
	HolidayDate dateDefinition

	LocalDate getDate(int year) {
		dateDefinition.getDate(year)
	}

}
