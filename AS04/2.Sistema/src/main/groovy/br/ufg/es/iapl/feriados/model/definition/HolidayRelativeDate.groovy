package br.ufg.es.iapl.feriados.model.definition

import br.ufg.es.iapl.feriados.model.Holiday
import groovy.transform.CompileStatic
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import java.time.LocalDate

@Entity
@CompileStatic
class HolidayRelativeDate extends DateDefinition {

	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = 'holiday_id', nullable = true)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	Holiday holiday

	@Column(nullable = true)
	int daysOffset

	@Override
	LocalDate getDate(int year) {
		LocalDate referenceHolidayDate = holiday.getDate(year)

		return referenceHolidayDate.plusDays(daysOffset)
	}
}
