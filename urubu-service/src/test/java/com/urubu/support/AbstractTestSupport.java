package com.urubu.support;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.urubu.domain.entity.test.support.AbstractTestDbSetup;
import com.urubu.service.config.ServiceConfig;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;


@EnableJpaRepositories
@ContextConfiguration(classes = { ServiceConfig.class })
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AbstractTestSupport extends AbstractTestDbSetup {

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	protected ModelMapper modelMapper;


}
