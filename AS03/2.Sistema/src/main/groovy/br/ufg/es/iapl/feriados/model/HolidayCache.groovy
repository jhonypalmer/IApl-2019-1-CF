package br.ufg.es.iapl.feriados.model


import br.ufg.es.iapl.feriados.model.region.Region
import groovy.transform.CompileStatic
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

import javax.persistence.*
import javax.validation.constraints.NotBlank
import java.time.LocalDate

@Entity
@CompileStatic
class HolidayCache {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = 'region_id', nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	Region region

	@NotBlank
	@Column(columnDefinition = 'text')
	String description

	@Column
	LocalDate date

}
