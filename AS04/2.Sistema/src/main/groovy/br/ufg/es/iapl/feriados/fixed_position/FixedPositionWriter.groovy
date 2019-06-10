package br.ufg.es.iapl.feriados.fixed_position

import br.ufg.es.iapl.feriados.dto.HolidayDTO
import br.ufg.es.iapl.feriados.dto.HolidaysDTO
import groovy.transform.CompileStatic

import java.time.LocalDate
import java.time.format.DateTimeFormatter

@CompileStatic
class FixedPositionWriter {

	private HolidaysDTO holidaysDTO

	FixedPositionWriter(HolidaysDTO holidaysDTO) {
		this.holidaysDTO = holidaysDTO
	}

	private static final DateTimeFormatter DATE_FORMATER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	String write() {
		StringBuilder stringBuilder = new StringBuilder()
		stringBuilder << writeHeader()
		stringBuilder << createDetail()
		stringBuilder << createTrailler()

		return stringBuilder.toString()
	}

	private String writeHeader() {
		StringBuilder sb = new StringBuilder()
		sb << 'Holidays'.padRight(8)
		sb << DATE_FORMATER.format(LocalDate.now()).padRight(10)
		sb << '\n'
		return sb.toString()
	}

	private String createDetail() {
		StringBuilder sb = new StringBuilder()

		for (HolidayDTO holidayDTO : holidaysDTO.getHolidays()) {
			sb.append(holidayDTO.getDescription().padRight(60))

			sb << DATE_FORMATER.format(holidayDTO.date).padRight(10)
			sb << (holidayDTO.city ?: '').padRight(40)
			sb << (holidayDTO.state ?: '').padRight(60)
			sb << (holidayDTO.country ?: '').padRight(50)
			sb.append("\n")
		}

		return sb.toString()
	}

	private String createTrailler() {
		return String.valueOf(holidaysDTO.holidays.size()).padRight(10)
	}
}
