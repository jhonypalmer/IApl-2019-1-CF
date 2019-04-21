package br.ufg.es.iapl.feriados.model

import br.ufg.es.iapl.feriados.model.definition.DateDefinition
import br.ufg.es.iapl.feriados.model.region.Region

import java.time.LocalDate

class Holiday {

	Region region
	String description
	DateDefinition dateDefinition

	LocalDate getDate(int year) {
		dateDefinition.getDate(year)
	}

}
