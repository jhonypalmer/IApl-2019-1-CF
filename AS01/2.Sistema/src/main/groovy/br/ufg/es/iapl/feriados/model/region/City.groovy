package br.ufg.es.iapl.feriados.model.region

import groovy.transform.CompileStatic
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

import javax.persistence.*

@Entity
@CompileStatic
class City extends Region {

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = 'state_id', nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	State state

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = 'country_id', nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	Country country

}
