package br.ufg.es.iapl.feriados.dto

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "holidays")
class HolidaysDTO {

	Integer year

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "holiday")
	List<HolidayDTO> holidays

}
