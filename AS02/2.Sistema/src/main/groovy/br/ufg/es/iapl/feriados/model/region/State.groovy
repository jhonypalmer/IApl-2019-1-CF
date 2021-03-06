package br.ufg.es.iapl.feriados.model.region

import groovy.transform.CompileStatic
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
@CompileStatic
class State extends Region {

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = 'country_id', nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	Country country

}
