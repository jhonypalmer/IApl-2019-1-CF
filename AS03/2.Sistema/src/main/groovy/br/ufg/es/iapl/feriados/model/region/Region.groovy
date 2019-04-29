package br.ufg.es.iapl.feriados.model.region

import groovy.transform.CompileStatic

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@CompileStatic
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class Region {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id

	@NotBlank
	String name

}
