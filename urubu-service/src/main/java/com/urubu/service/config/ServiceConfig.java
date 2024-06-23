package com.urubu.service.config;


import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.urubu.core.config.CoreSettings;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableJpaRepositories("com.urubu.domain.repository")
@EntityScan("com.urubu.domain.entity")
@ComponentScan(basePackages = {
		"com.urubu.service.impl",
		"com.urubu.service.converter",
		"com.urubu.doamin.config",})
@Import(CoreSettings.class)
@EnableAutoConfiguration(exclude = { QuartzAutoConfiguration.class })
public class ServiceConfig {

	@Bean
	public Validator localValidatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return JsonMapper.builder()
				.addModule(new JavaTimeModule())
				.disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS)
				.build();
	}

	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

	@Bean
	public RestTemplate restTemplateNoHeaders(ClientHttpRequestFactory clientHttpRequestFactory) {
		RestTemplate restTemplateNoHeaders = new RestTemplate(clientHttpRequestFactory);
		List<ClientHttpRequestInterceptor> noHeadersInterceptors = new ArrayList<>();
		restTemplateNoHeaders.setInterceptors(noHeadersInterceptors);
		return restTemplateNoHeaders;
	}

	@Bean
	public ClientHttpRequestFactory clientHttpRequestFactory() {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		return new BufferingClientHttpRequestFactory(requestFactory);
	}

}
