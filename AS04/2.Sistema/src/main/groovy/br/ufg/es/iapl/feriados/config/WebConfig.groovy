package br.ufg.es.iapl.feriados.config

import br.ufg.es.iapl.feriados.converter.FixedPositionConverter
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
@ComponentScan
class WebConfig implements WebMvcConfigurer {

	@Override
	void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/")
		registry
				.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/")
		registry
				.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/")
	}

	@Override
	void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

		configurer.favorPathExtension(false).
				favorParameter(true).
				parameterName("mediaType").
				ignoreAcceptHeader(true).
				useRegisteredExtensionsOnly(true).
				defaultContentType(MediaType.APPLICATION_JSON).
				mediaType("xml", MediaType.APPLICATION_XML).
				mediaType("json", MediaType.APPLICATION_JSON).
				mediaType("fixedPosition", new MediaType("application", "fixedPosition"))
	}

	@Override
	void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new FixedPositionConverter())
	}
}
