package br.ufg.es.iapl.feriados.converter

import br.ufg.es.iapl.feriados.dto.AppliedHoliday
import br.ufg.es.iapl.feriados.dto.AppliedHolidayResultList
import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.math.NumberUtils
import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.AbstractHttpMessageConverter
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.http.converter.HttpMessageNotWritableException

import java.security.InvalidParameterException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class FixedPositionConverter extends AbstractHttpMessageConverter<AppliedHolidayResultList> {

	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy", new Locale("pt", "BR"))

	FixedPositionConverter() {
		super(new MediaType("application", "fixedPosition"))
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return AppliedHolidayResultList.class.isAssignableFrom(clazz)
	}

	@Override
	protected AppliedHolidayResultList readInternal(Class<? extends AppliedHolidayResultList> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		String requestBody = toString(inputMessage.getBody())
		int i = requestBody.indexOf("\n")
		if (i == -1) {
			throw new HttpMessageNotReadableException("No first line found", inputMessage)
		}

		String header = requestBody.substring(0, i).trim()
		String content = requestBody.substring(i).trim()

		AppliedHolidayResultList holidaysDTO = new AppliedHolidayResultList()
		List<AppliedHoliday> holidays = new ArrayList<>()

		String[] linhas = content.split("\n")
		for (String linha : linhas) {

			if (NumberUtils.isNumber(linha)) {
				Integer quantityHolidays = Integer.parseInt(linha)

				if (!quantityHolidays.equals(linhas.length - 1)) {
					throw new InvalidParameterException("A quantidade informada no rodapé diverge da quantidade de feriados informados.")
				}
			} else {
				AppliedHoliday holidayDTO = new AppliedHoliday()

				//description
				String description = linha.substring(0, 59)
				holidayDTO.setDescription(description.trim())

				//date
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
				String date = linha.substring(60, 70)
				holidayDTO.setDate(LocalDate.parse(date, formatter))

				//city
				String city = linha.substring(70, 130)
				holidayDTO.setCity(city.trim())

				//state
				String state = linha.substring(130, 190)
				holidayDTO.setState(state.trim())

				//country
				String country = linha.substring(190, 250)
				holidayDTO.setCountry(country.trim())

				holidays.add(holidayDTO)
			}
		}

		holidaysDTO.setHolidays(holidays)
		return holidaysDTO
	}

	@Override
	protected void writeInternal(AppliedHolidayResultList holidaysDTO, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		try {
			OutputStream outputStream = outputMessage.getBody()
			String body = convertHolidayToLayoutFixedPosition(holidaysDTO)
			outputStream.write(body.getBytes())
			outputStream.close()
		} catch (Exception e) {
		}
	}

	private static String toString(InputStream inputStream) {
		Scanner scanner = new Scanner(inputStream, "UTF-8")
		return scanner.useDelimiter("\\A").next()
	}

	private String convertHolidayToLayoutFixedPosition(AppliedHolidayResultList holidaysDTO) {
		StringBuilder sb = new StringBuilder()
		sb.append(createHeader(holidaysDTO))
		sb.append(createDetail(holidaysDTO))
		sb.append(createTrailler(holidaysDTO))
		return sb.toString()
	}

	private String createHeader(AppliedHolidayResultList holidaysDTO) {
		StringBuilder sb = new StringBuilder()
		sb.append(StringUtils.rightPad("Holidays", 8, " "))
		sb.append(StringUtils.rightPad(holidaysDTO.year.toString(), 10, " "))
		sb.append(StringUtils.rightPad(SIMPLE_DATE_FORMAT.format(new Date()), 10, " "))
		sb.append("\n")
		return sb.toString()
	}

	private String createDetail(AppliedHolidayResultList holidaysDTO) {
		StringBuilder sb = new StringBuilder()

		for (AppliedHoliday holidayDTO : holidaysDTO.getHolidays()) {
			sb.append(StringUtils.rightPad(holidayDTO.getDescription(), 60, " "))

			Date date = Date.from(holidayDTO.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant())

			sb.append(StringUtils.rightPad(SIMPLE_DATE_FORMAT.format(date), 10, " "))
			sb.append(StringUtils.rightPad(holidayDTO.getCity() == null ? "" : holidayDTO.getCity(), 40, " "))
			sb.append(StringUtils.rightPad(holidayDTO.getState() == null ? "" : holidayDTO.getState(), 60, " "))
			sb.append(StringUtils.rightPad(holidayDTO.getCountry() == null ? "" : holidayDTO.getCountry(), 50, " "))
			sb.append("\n")
		}

		return sb.toString()
	}

	private String createTrailler(AppliedHolidayResultList holidaysDTO) {
		StringBuilder sb = new StringBuilder()
		sb.append(StringUtils.rightPad(String.valueOf(holidaysDTO.getHolidays().size()), 10, " "))
		return sb.toString()
	}
}
