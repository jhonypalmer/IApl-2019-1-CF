package br.ufg.es.iapl.feriados.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import groovy.transform.CompileStatic

import java.time.LocalDate

@CompileStatic
@JacksonXmlRootElement(localName = "holiday")
class AppliedHoliday {

	String description

	@JsonFormat(pattern = 'yyyy-MM-dd')
	@JsonDeserialize(using = LocalDateDeserializer)
	@JsonSerialize(using = LocalDateSerializer)
	LocalDate date

	String city
	String state
	String country
}
