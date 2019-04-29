package br.ufg.es.iapl.feriados.service


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HolidayCacheServiceSpec extends Specification {

	@Autowired
	HolidayCacheService holidayCacheService

	def 'Deve criar o arquivo posicional corretamente'() {
		setup:
		holidayCacheService.buildHolidayCache(2019)

		when:
		String positionalFile = holidayCacheService.buildPositionalFile()

		then:
		positionalFile == expectedFile

		where:
		expectedFile = this.class.getResource('/positional.txt').text
	}

	def 'Deve ler o arquivo posicional corretamente'() {
		setup:
		holidayCacheService.buildHolidayCache(2019)

		when:
		String positionalFile = holidayCacheService.buildPositionalFile()

		then:
		positionalFile == expectedFile

		where:
		expectedFile = this.class.getResource('/positional.txt').text
	}

}
