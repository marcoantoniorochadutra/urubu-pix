package com.urubu.domain.entity.test.support;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
@Transactional
public abstract class AbstractTestDbSetup {

	private static final Logger log = LoggerFactory.getLogger(AbstractTestDbSetup.class);

	@Container
	static MySQLContainer<?> mySqlContainer = new MySQLContainer<>("mysql:8.0");

	@BeforeAll
	static void startDb() {
		mySqlContainer.start();
		System.setProperty("DATASOURCE_URL", mySqlContainer.getJdbcUrl());
		System.setProperty("DATASOURCE_PASSWORD", mySqlContainer.getPassword());
		System.setProperty("DATASOURCE_USERNAME", mySqlContainer.getUsername());
		System.setProperty("HIBERNATE_DDL_OPTION", "none");

	}

}
