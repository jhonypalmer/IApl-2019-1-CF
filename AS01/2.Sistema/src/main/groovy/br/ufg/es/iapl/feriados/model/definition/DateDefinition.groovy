package br.ufg.es.iapl.feriados.model.definition

import java.time.LocalDate

interface DateDefinition {
	LocalDate getDate(int year)
}
