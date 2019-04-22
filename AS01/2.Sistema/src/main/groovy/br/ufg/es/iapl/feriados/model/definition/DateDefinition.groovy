package br.ufg.es.iapl.feriados.model.definition

import groovy.transform.CompileStatic

import javax.persistence.*
import java.time.LocalDate

@Entity
@CompileStatic
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
abstract class DateDefinition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id

	abstract LocalDate getDate(int year)
}
