package br.ufg.es.iapl.feriados.dto

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import groovy.transform.CompileStatic

@CompileStatic
@JacksonXmlRootElement(localName = "holidays")
class AppliedHolidayResultList {

	Integer year

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "holiday")
	List<AppliedHoliday> holidays

	Long total

}
