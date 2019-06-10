package br.ufg.es.iapl.feriados.model.region

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.validation.constraints.NotBlank

@Entity
@CompileStatic
class Country extends Region {

	@NotBlank
	String abbreviation

}
