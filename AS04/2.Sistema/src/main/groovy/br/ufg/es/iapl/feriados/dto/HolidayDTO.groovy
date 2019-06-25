package br.ufg.es.iapl.feriados.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

import java.time.LocalDate

@JacksonXmlRootElement(localName = "holiday")
class HolidayDTO {

	Long id

	String description

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	LocalDate date

	String city
	String state
	String country
}
