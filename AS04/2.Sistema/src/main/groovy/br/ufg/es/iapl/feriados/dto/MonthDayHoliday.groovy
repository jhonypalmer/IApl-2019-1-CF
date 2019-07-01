package br.ufg.es.iapl.feriados.dto

import groovy.transform.CompileStatic

@CompileStatic
class MonthDayHoliday {

	Long id
	String description

	int month
	int dayOfMonth
	String city
	String state
	String country

}
