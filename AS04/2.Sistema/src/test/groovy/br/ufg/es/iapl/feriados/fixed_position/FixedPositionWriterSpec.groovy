package br.ufg.es.iapl.feriados.fixed_position

import br.ufg.es.iapl.feriados.dto.AppliedHolidayResultList
import br.ufg.es.iapl.feriados.service.AppliedHolidayService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration
@ActiveProfiles('test')
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FixedPositionWriterSpec extends Specification {

	@Autowired
	AppliedHolidayService holidayService

	def 'Deve criar o arquivo posicional corretamente'() {
		setup:
		AppliedHolidayResultList holidaysDTO = holidayService.listAppliedHolidayByYear(2019)
		FixedPositionWriter fixedPositionWriter = new FixedPositionWriter(holidaysDTO)

		when:
		String positionalFile = fixedPositionWriter.write()

		then:
		positionalFile == expectedFile

		where:
		expectedFile = this.class.getResource('/positional.txt').text
	}
}
