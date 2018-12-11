package com.mixaaa.jackson.demo.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@AutoConfigureAfter(JacksonAutoConfiguration.class)
public class SecondObjectMapperAutoConfiguration {

	@ConfigurationProperties(prefix = "logging.jackson")
	@Bean("loggingJacksonProperties")
	public JacksonProperties loggingJacksonProperties() {
		return new JacksonProperties();
	}

	@Bean
	public ObjectMapper secondObjectMapper() {
		return loggingJacksonProperties().initializeJackson2ObjectMapperBuilder().build();
	}

	@Bean
	public RestTemplate secondRestTemplate(ObjectMapper secondObjectMapper) {
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
		messageConverter.setPrettyPrint(false);
		messageConverter.setObjectMapper(secondObjectMapper);
		RestTemplate secondRestTemplate = new RestTemplate();
		secondRestTemplate.getMessageConverters().add(messageConverter);
		return secondRestTemplate;
	}

}
